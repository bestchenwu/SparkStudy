package com.kafkaStudy.producer.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 在消息发送完成后计算成功发送次数和失败次数
 *
 * @author chenwu on 2020.2.2
 */
public class CounterInterceptor implements ProducerInterceptor<String,String> {

    private int successCount = 0;
    private int errorCount = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if(exception == null){
            successCount+=1;
        }else{
            errorCount+=1;
        }
    }

    @Override
    public void close() {
        System.out.println("成功发送:"+successCount);
        System.out.println("错误发送:"+errorCount);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
