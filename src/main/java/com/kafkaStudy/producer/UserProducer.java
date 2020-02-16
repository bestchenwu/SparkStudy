package com.kafkaStudy.producer;

import com.kafkaStudy.model.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        //User user = new User("hello","sweet",1,"xiaogan");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        try{
            while(true){
                line = reader.readLine();
                if("exit"==line){
                    break;
                }
                String[] array = line.split(",");
                User user = new User(array[0],array[1],Integer.parseInt(array[2]),array[3]);
                ProducerRecord<String, User> record = new ProducerRecord<>("test-user", user);
                producer.send(record);
            }

        }catch(IOException e){

        }
        producer.close();
    }
}
