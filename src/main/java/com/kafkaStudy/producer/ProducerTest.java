package com.kafkaStudy.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
        //指定partitioner class                                                                                  
        props.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.kafkaStudy.producer.AuditPartitioner"); //acks=0 表示生产者完全不理会broker端的处理结果,这个时候回调函数就失去意义,消息吞吐量最高
        //acks=all/-1 //表示leader broker会将消息写入本地日志,同时会等待ISR其他副本写入各自的本地日志,吞吐量是最低的，消息持久性是最高的
        //acks=1 是默认值,发送消息后,leader broker只将消息写入本地日志,然后便发送响应结果给生产者
        //props.setProperty("acks","0");
        //设置消息的压缩格式,会降低消息量的大小，但是会加大生产者的CPU负载
        //props.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG,"lz4");
        //批量消息的默认大小,默认为16KB
        //props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,"1048576");
        //消息发送延迟控制,默认是0,表示立即被发送
        //props.setProperty(ProducerConfig.LINGER_MS_CONFIG,"100");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
//        for (int i = 0; i < 10; i++) {
//            //这里先后指定了分区partitionId,消息内容的key-value
//            producer.send(new ProducerRecord<>("test-flink", i, Integer.toString(i), Integer.toString(i)));
//            //使用callback
//            ProducerRecord<String, String> record = new ProducerRecord<String, String>("", "");
//            producer.send(record, new Callback() {
//                @Override
//                public void onCompletion(RecordMetadata metadata, Exception exception) {
//                    if(exception == null){
//                        //消息发送成功
//                    }else{
//                        //消息发送失败
//                        producer.close(0, TimeUnit.MILLISECONDS);
//                    }
//                }
//            });
        //}


        ProducerRecord<String, String> record1 = new ProducerRecord<String, String>("test-flink","no key", "haha1");
        ProducerRecord<String, String> record2 = new ProducerRecord<String, String>("test-flink","audit", "haha2");
        ProducerRecord<String, String> record3 = new ProducerRecord<String, String>("test-flink","no key", "haha3");
        ProducerRecord<String, String> record4 = new ProducerRecord<String, String>("test-flink","Audit", "haha4");
        ProducerRecord<String, String> record5 = new ProducerRecord<String, String>("test-flink","no key", "haha5");
        producer.send(record1);
        producer.send(record2);
        producer.send(record3);
        producer.send(record4);
        producer.send(record5);
        producer.close();
    }
}
