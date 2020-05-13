package com.redisStudy.appliance1;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * redis的分布式锁的学习
 *
 * @author chenwu on 2020.5.13
 */
public class SetNxStudy {

    private CommonRedissionClient commonRedissionClient;
    private String key = "testNx20200513";

    @Before
    public void init() {
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    @Test
    public void testSetNx() {

        List<SetNxThread> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            SetNxThread setNxThread = new SetNxThread(commonRedissionClient, key, String.valueOf(i));
            list.add(setNxThread);
        }
        Collections.shuffle(list);
        for (SetNxThread thread : list) {
            //todo:这里为啥不能用线程?是因为junit方法执行完成后就把线程kill了嘛
            //thread.start();
            thread.run();
        }
    }

    @After
    public void close() {
        String value = commonRedissionClient.getSingleKey(key, String.class);
        System.out.println("final result:" + value);
        commonRedissionClient.close();
    }
}
