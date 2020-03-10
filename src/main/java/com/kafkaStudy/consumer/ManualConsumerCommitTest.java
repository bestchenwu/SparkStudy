package com.kafkaStudy.consumer;

//import com.mysql.MysqlClient;
import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.*;

import org.apache.kafka.common.TopicPartition;
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
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test-flink-consumer0");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //从最早开始接受
        //properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        //从最新位移处开始接受
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("test-flink"));
        List<String> values = new ArrayList<>();
//        try {
//            while (true) {
//                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(100l));
//                int count = consumerRecords.count();
//                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
//                    String key = consumerRecord.key();
//                    String value = consumerRecord.value();
//                    System.out.println("accepted value:"+value);
//                    values.add(value);
//                }
//                insertIntoDB(values);
//                System.out.println("insert success count:"+count);
//                consumer.commitAsync();
//                values.clear();
//            }
//        } finally {
//            consumer.close();
//        }

        //更为精细到topic partition的提交方式是:
        try{
            while(true){
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(100l));
                for(TopicPartition topicPartition : consumerRecords.partitions()){
                    //对于具体的某个topic 某个partition 进行消息消费
                    List<ConsumerRecord<String, String>> partitionRecords = consumerRecords.records(topicPartition);
                    for(ConsumerRecord<String,String> record : partitionRecords){
                        System.out.println("key:"+record.key()+",value:"+record.value());
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size()-1).offset();
                    //下一条待读取的位移
                    OffsetAndMetadata newOffsetAndMetadata = new OffsetAndMetadata(lastOffset + 1);
                    consumer.commitSync(Collections.singletonMap(topicPartition,newOffsetAndMetadata));
                }
            }
        }finally {
            consumer.close();
        }
    }

    private static void insertIntoDB(List<String> values) {
        //MysqlClient mysqlClient = new MysqlClient("localhost", 3306, "test", "123456", "test");
//        String sql = mysqlClient.getInsertOrUpdateSql("student",JavaConversions.asScalaBuffer(Arrays.asList("name","age")));
//        List<Buffer<Object>> columnValues = new ArrayList<>();
//        for(String value : values){
//            String[] splitArray = value.split(",");
//            Buffer<Object> stringBuffer = JavaConversions.asScalaBuffer(Arrays.asList(splitArray[0],Integer.parseInt(splitArray[1])));
//            columnValues.add(stringBuffer);
//        }
//        mysqlClient.batchInsertIntoTable("student",JavaConversions.asScalaBuffer(Arrays.asList("name","age")),JavaConversions.asScalaBuffer(columnValues));
    }
}
