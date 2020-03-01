package com.kafkaStudy.producer;

import org.apache.flink.runtime.io.network.partition.ProducerFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.Future;

/**
 * 如何准确的实现发送一次消息
 *
 * @author chenwu on 2020.3.1
 */
public class ExactlyOnceProducer {

    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //指定partitioner class
        props.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.kafkaStudy.producer.AuditPartitioner");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("test-topic"));
        //开启事务输出
        producer.initTransactions();
        try{
            producer.beginTransaction();
            //发送消息
            ProducerRecord<String,String> record = new ProducerRecord<>("test-topic","key1","value1");
            Future<RecordMetadata> recordMetadataFuture = producer.send(record);
            //todo:如果只负责生产端的准确一次,则判断recordMetadataFuture的正确与否即可
            //RecordMetadata recordMetadata = recordMetadataFuture.get();
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(100l));
            //生产端的准确一次
            Map<TopicPartition, OffsetAndMetadata> consumerOffsets = null;
            for(TopicPartition topicPartition :consumerRecords.partitions()){
                List<ConsumerRecord<String, String>> partitionRecords = consumerRecords.records(topicPartition);
                for(ConsumerRecord<String,String> recordItem :partitionRecords){
                    //消费端的准确一次
                    long lastOffset = partitionRecords.get(partitionRecords.size()-1).offset();
                    //下一条待读取的位移
                    OffsetAndMetadata newOffsetAndMetadata = new OffsetAndMetadata(lastOffset + 1);
                    consumerOffsets = Collections.singletonMap(topicPartition,newOffsetAndMetadata);
                    consumer.commitSync(consumerOffsets);
                    //写入一条记录给kafka producer端
                    //todo:将consumerOffsets发送给producer 里面记录哪个topic 哪个partition 哪个group
                    consumer.commitSync();
                }
            }
            //生产端与消费端均只准确一次
            producer.sendOffsetsToTransaction(consumerOffsets,"consumerGroup");
            producer.commitTransaction();
        }catch(ProducerFencedException e){
            producer.close();
        }catch(KafkaException e1) {
            producer.abortTransaction();
        }
    }
}
