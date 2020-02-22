package com.kafkaStudy.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 给consumer指定固定的分区
 *
 * @author chenwu on 2020.2.22
 */
public class FixedPartitionConsumerTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"test-flink-group");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"false");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeSerializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeSerializer");
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);
        List<PartitionInfo> partitionInfos = consumer.partitionsFor("test-flink");
        List<TopicPartition> topicPartitionList = new ArrayList<>();
        for(PartitionInfo partitionInfo : partitionInfos){
            topicPartitionList.add(new TopicPartition("test-flink",partitionInfo.partition()));
            consumer.assign(topicPartitionList);
        }
        //注意这里的assign  后面一次的assign会覆盖前一次的
        try{
            while(true){
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(100L));
                for(ConsumerRecord<String,String> record : consumerRecords){
                    //todo:消费消息
                }
                consumer.commitSync();
            }
        }finally {
            consumer.wakeup();
            consumer.commitSync();
        }
    }
}
