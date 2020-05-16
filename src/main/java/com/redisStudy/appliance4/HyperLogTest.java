package com.redisStudy.appliance4;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * redis的HyperLog基数估算法
 *
 * @author chenwu on 2020.5.16
 */
public class HyperLogTest {

    private CommonRedissionClient commonRedissionClient;

    @Before
    public void init() {
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    @Test
    public void testHyperLog(){
        String key = "testHyperLog0";
        long result = 0;
        for(int i = 0;i<1000*100;i++){
            result = commonRedissionClient.addHyperLog(key,i,Integer.class,5, TimeUnit.MINUTES);
        }
        System.out.println("result="+result);
    }

    @After
    public void close() {
        commonRedissionClient.close();
    }
}
