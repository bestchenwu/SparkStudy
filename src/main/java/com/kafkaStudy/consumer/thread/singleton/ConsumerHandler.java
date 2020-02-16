package com.kafkaStudy.consumer.thread.singleton;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerHandler {

    private  KafkaConsumer<String,String> kafkaConsumer;
    private Map<TopicPartition, OffsetAndMetadata> offsets;
    private int workerNum;
    private ExecutorService works;

    public ConsumerHandler(Properties properties,String topic,int workNum){
        this.workerNum = workNum;
        //多个消息消费线程工作,这里必须手动管理位移
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"false");
        kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
        works = Executors.newFixedThreadPool(workNum);
    }

    public void start(){
        try{
            while(true){
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(100l));
                if(!consumerRecords.isEmpty()){
                    //offsets作为一个中间使者,传递位移信息
                    KafkaWorker kafkaWorker = new KafkaWorker(consumerRecords,offsets);
                    works.submit(kafkaWorker);
                }
                //提交位移
                commitOffsets();
            }
        }finally {
            works.shutdown();
            kafkaConsumer.close();
        }
    }

    private void commitOffsets(){
        Map<TopicPartition,OffsetAndMetadata> unmodifiedMap;
        //使用对offsets进行同步加锁,减少对消费者线程的影响
        synchronized (offsets){
            if(offsets.isEmpty()){
                return;
            }
            unmodifiedMap = Collections.unmodifiableMap(offsets);
            offsets.clear();
        }
        kafkaConsumer.commitSync(unmodifiedMap);
    }

    public void close(){
        kafkaConsumer.wakeup();
        works.shutdown();
    }
}
