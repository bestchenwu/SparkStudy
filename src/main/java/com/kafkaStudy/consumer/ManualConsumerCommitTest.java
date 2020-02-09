package com.kafkaStudy.consumer;

import com.mysql.MysqlClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import scala.collection.JavaConversions;
import scala.collection.mutable.Buffer;

/**
 * 手动提交消费者
 *
 * @author chenwu on 2020.2.9
 */
public class ManualConsumerCommitTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test-flink-consumer");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("test-flink"));
        List<String> values = new ArrayList<>();
        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(100l));
                int count = consumerRecords.count();
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    String key = consumerRecord.key();
                    String value = consumerRecord.value();
                    values.add(value);
                }
                insertIntoDB(values);
                consumer.commitAsync();
                values.clear();
            }
        } finally {
            consumer.close();
        }
    }

    private static void insertIntoDB(List<String> values) {
        MysqlClient mysqlClient = new MysqlClient("localhost", 3306, "test", "123456", "test");
        String sql = mysqlClient.getInsertOrUpdateSql("student",JavaConversions.asScalaBuffer(Arrays.asList("name","age")));
        List<Buffer<String>> columnValues = new ArrayList<>();
        for(String value : values){
            String[] splitArray = value.split(",");
            Buffer<String> stringBuffer = JavaConversions.asScalaBuffer(Arrays.asList(splitArray));
            columnValues.add(stringBuffer);
        }
        mysqlClient.batchInsertIntoTable("student",JavaConversions.asScalaBuffer(Arrays.asList("name","age")),JavaConversions.asScalaBuffer(columnValues));
    }
}
