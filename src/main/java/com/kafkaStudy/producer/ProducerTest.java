package com.kafkaStudy.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 生产者的测试类
 *
 * @author chenwu on 2020.1.22
 */
public class ProducerTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("test-flink", Integer.toString(i), Integer.toString(i)));
        }
        producer.close();
    }
}
