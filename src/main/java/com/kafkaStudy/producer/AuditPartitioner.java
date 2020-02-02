package com.kafkaStudy.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 实现当key值是单词audit的时候发送到最后一个分区,否则随机发送
 *
 * @author chenwu on 2020.2.2
 */
public class AuditPartitioner implements Partitioner {

    private Random random;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String keyStr = (String)key;
        List<PartitionInfo> partitionInfos = cluster.availablePartitionsForTopic(topic);
        int partitionCount = partitionInfos.size();
        int auditPartition = partitionCount-1;//将单词audit代表的key发送到最后一个分区
        if("audit".equalsIgnoreCase(keyStr)){
            return auditPartition;
        }else{
            return random.nextInt(partitionCount-1);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {
        random = new Random(47);
    }
}
