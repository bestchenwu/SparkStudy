package com.redisStudy.appliance1;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * redis的分布式锁的学习
 */
public class SetNxStudy {

    private CommonRedissionClient commonRedissionClient;

    @Before
    public void init(){
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    @Test
    public void testSetNx(){
        String key = "testNx";
        List<SetNxThread> list = new ArrayList<>();
        for(int i = 1;i<=1;i++){
            SetNxThread setNxThread = new SetNxThread(commonRedissionClient,key,Integer.valueOf(i));
            list.add(setNxThread);
        }
        Collections.shuffle(list);
        for(SetNxThread thread:list){
            thread.start();
        }
    }
}
