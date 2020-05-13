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

    @Test
    public void testTimeQueue(){
        List<String> list = Arrays.asList("aa","bb","cc","dd");
        String key = "testQueue";
        list.stream().forEach(item->commonRedissionClient.addDeque(key,item,10, TimeUnit.SECONDS));
        System.out.println(commonRedissionClient.pollDeque(key,true,String.class));
        System.out.println(commonRedissionClient.pollDeque(key,false,String.class));
        System.out.println(commonRedissionClient.pollDeque(key,false,String.class));
        System.out.println(commonRedissionClient.pollDeque(key,true,String.class));
    }

    @After
    public void close() {
        commonRedissionClient.close();
    }
}
