package com.kafkaStudy.consumer.thread.multi;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerRunnable implements Runnable{

    private KafkaConsumer<String,String> kafkaConsumer;

    public ConsumerRunnable(Properties properties,String topic){
        kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        try{
            while(true){
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(100l));
                for(ConsumerRecord<String,String> consumerRecord : consumerRecords){
                    //todo:在这里消费消息
                }
            }
        }finally {
            kafkaConsumer.close();
        }
    }
}
