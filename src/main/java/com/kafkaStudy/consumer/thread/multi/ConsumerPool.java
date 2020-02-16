package com.kafkaStudy.consumer.thread.multi;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 消费者线程池
 *
 * @author chenwu on 2020.2.16
 */
public class ConsumerPool {

    public ExecutorService executorService;
    private Properties properties;
    private String topic;

    public ConsumerPool(int num, Properties properties, String topic){
        executorService = Executors.newFixedThreadPool(num);
        this.properties = properties;
        this.topic = topic;
    }

    public void start(){
        executorService.submit(new ConsumerRunnable(properties,topic));
    }

    public void close(){
        executorService.shutdownNow();
    }
}
