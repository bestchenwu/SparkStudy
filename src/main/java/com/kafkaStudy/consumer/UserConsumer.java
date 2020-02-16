package com.kafkaStudy.consumer;

import com.kafkaStudy.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * {@link User}的消费类
 */
public class UserConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"test-user-group");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"com.kafkaStudy.consumer.UserDeserializer");
        KafkaConsumer<String,User> kafkaConsumer = new KafkaConsumer<String, User>(properties);
        kafkaConsumer.subscribe(Arrays.asList("test-user"));
        try{
            while(true){
                ConsumerRecords<String, User> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(100l));
                for(ConsumerRecord<String,User> consumerRecord : consumerRecords){
                    System.out.println(consumerRecord.value());
                }
            }
        }finally {
            kafkaConsumer.close();
        }


    }
}
