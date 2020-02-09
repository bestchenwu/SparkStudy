package com.kafkaStudy.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Consumer的测试
 *
 * @author chenwu on 2020.2.2
 */
public class ConsumerTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"test-flink-consumer");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        //是否自动提交位移,如果需要exactly once语义,这里需要设置为false
        //properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("test-flink"));
        try{
            while (true){
                //10秒表示consumer允许等待的拿到足够多的可用数据的等待时间
                ConsumerRecords<String, String> pollRecords = consumer.poll(Duration.ofSeconds(10));
                for(ConsumerRecord<String,String> record : pollRecords){
                    System.out.printf("offset=%d,key=%s,value=%s",record.offset(),record.key(),record.value());
                }
            }
        }finally {
            consumer.close(Duration.ofMillis(0));
        }
    }
}
