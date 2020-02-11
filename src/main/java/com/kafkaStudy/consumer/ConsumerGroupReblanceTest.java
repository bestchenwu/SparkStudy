package com.kafkaStudy.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Consumer Group的reblance测试
 *
 * @author chenwu on 2020.2.11
 */
public class ConsumerGroupReblanceTest {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"test-reblance");
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        AtomicLong joinStart = new AtomicLong(0l);
        kafkaConsumer.subscribe(Arrays.asList("test-flink"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                //当partition发生变化的时候,也就是消息被消费的时候
                for(TopicPartition topicPartition : partitions){
                    saveOffsetOutside( topicPartition.topic(),topicPartition.partition(),kafkaConsumer.position(topicPartition));
                }
                joinStart.set(System.currentTimeMillis());
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                //todo:如果没有发生重分配,如何让一个consumer继续消费
                //当partition发生重分配的时候,重新获取offset
                for(TopicPartition topicPartition : partitions){
                    long offset = readOffsetOutside(topicPartition.topic(),topicPartition.partition());
                    kafkaConsumer.seek(topicPartition,offset);
                }
                joinStart.set(System.currentTimeMillis());
            }
        });
        try{
            while(true){
                ConsumerRecords<String,String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(100l));
                for(ConsumerRecord<String,String> consumerRecord : consumerRecords){
                    System.out.println("record[key="+consumerRecord.key()+",value=["+consumerRecord.value()+"]");
                }
            }
        }catch(Exception e){

        }finally {
            kafkaConsumer.close();
        }
    }

    /**
     * 将位移保存到外部
     *
     * @param topic
     * @param partition
     * @param position
     */
    private static void saveOffsetOutside(String topic,int partition,long position){
        //todo:
    }

    /**
     * 从外部读取offset
     *
     * @param topic
     * @param partition
     * @return
     */
    private static long readOffsetOutside(String topic,int partition){
        //todo:
        return 0l;
    }
}
