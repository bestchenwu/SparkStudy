package com.kafkaStudy.producer;

import com.kafkaStudy.model.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 使用指定的value序列化
 *
 * @author chenwu on 2020.2.2
 */
public class UserProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "com.kafkaStudy.producer.UserSerializer");
        //指定partitioner class
        //props.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.kafkaStudy.producer.AuditPartitioner");
        KafkaProducer<String, User> producer = new KafkaProducer<String, User>(props);
        User user = new User("hello","sweet",1,"xiaogan");
        ProducerRecord<String, User> record = new ProducerRecord<>("test-flink", user);
        producer.send(record);
        producer.close();
    }
}
