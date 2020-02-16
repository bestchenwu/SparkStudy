package com.kafkaStudy.consumer.thread.singleton;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * kafka消息处理类
 *
 * @author chenwu on 2020.2.16
 */
public class KafkaWorker implements Runnable {

    private ConsumerRecords<String, String> consumerRecords;
    private Map<TopicPartition, OffsetAndMetadata> offsets;

    public KafkaWorker(ConsumerRecords<String, String> consumerRecords, Map<TopicPartition, OffsetAndMetadata> offsets) {
        this.consumerRecords = consumerRecords;
        this.offsets = offsets;
    }

    @Override
    public void run() {
        for(TopicPartition topicPartition : consumerRecords.partitions()){
            List<ConsumerRecord<String, String>> partitionRecords = consumerRecords.records(topicPartition);
            for(ConsumerRecord<String,String> record : partitionRecords){
                //todo:处理消息,但是对于单线程的消费者线程来说,这里要做好幂等处理
            }
            long lastOffsets = partitionRecords.get(partitionRecords.size()-1).offset()+1;
            synchronized (offsets){
                if(!offsets.containsKey(topicPartition)){
                    offsets.put(topicPartition,new OffsetAndMetadata(lastOffsets+1));
                }else{
                    long curOffset = offsets.get(topicPartition).offset();
                    if(curOffset<lastOffsets){
                        offsets.put(topicPartition,new OffsetAndMetadata(lastOffsets+1));
                    }
                }
            }
        }

    }
}
