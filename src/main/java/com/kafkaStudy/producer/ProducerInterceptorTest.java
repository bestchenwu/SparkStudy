package com.kafkaStudy.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 发送消息的时候 使用拦截器
 *
 * @author chenwu on 2020.2.2
 */
public class ProducerInterceptorTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        List<String> interceptorList = new ArrayList<>();
        interceptorList.add("com.kafkaStudy.producer.interceptor.TimeStampPrependerInterceptor");
        interceptorList.add("com.kafkaStudy.producer.interceptor.CounterInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptorList);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i = 0;i<5;i++){
            ProducerRecord<String,String> record = new ProducerRecord<String,String>("test-flink",i,"test-"+i,String.valueOf(i));
            producer.send(record);
        }
        producer.close();
    }
}
