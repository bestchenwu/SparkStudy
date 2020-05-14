package com.redisStudy.appliance2;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列的应用
 */
public class TimeQueueTest {

    private CommonRedissionClient commonRedissionClient;

    @Before
    public void init() {
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    /**
     * 测试队列
     */
    @Test
    public void testDeque(){
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        String key = "testQueue";
        list.stream().forEach(item->commonRedissionClient.addDeque(key,item,10, TimeUnit.SECONDS));
        System.out.println(commonRedissionClient.pollDeque(key,true,String.class));
        System.out.println(commonRedissionClient.pollDeque(key,false,String.class));
        System.out.println(commonRedissionClient.pollDeque(key,false,String.class));
        System.out.println(commonRedissionClient.pollDeque(key,true,String.class));
    }

    /**
     * 测试阻塞队列
     */
    @Test
    public void testBlockQueue(){
        String key = "blockQueue";
        Thread producerThread = new Thread(){

            @Override
            public void run() {
                for(int i = 1;i<10;i++){
                    commonRedissionClient.addBlockQueue(key,i,Integer.class,10,TimeUnit.SECONDS);
                    try{
                        sleep(2*1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread consumerThread = new Thread(){

            @Override
            public void run() {
                while(true){
                    long startTime = System.currentTimeMillis();
                    try{
                        Integer value = commonRedissionClient.pollBlockQueue(key,10,TimeUnit.SECONDS);
                        long endTime = System.currentTimeMillis();
                        System.out.println("period:"+(endTime-startTime));
                        if(value!=null){
                            System.out.println("value:"+value);
                        }else{
                            break;
                        }
                    }catch(InterruptedException e){
                        e.printStackTrace();
                        break;
                    }

                }
            }
        };
        producerThread.start();
        consumerThread.start();
        while(producerThread.isAlive() || consumerThread.isAlive()){
            
        }
    }

    @After
    public void close() {
        commonRedissionClient.close();
    }
}
