package com.redisStudy.appliance9;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Redis的scan测试
 *
 * @author chenwu on 2020.8.2
 */
public class RedisScanTest {

    private CommonRedissionClient commonRedissionClient;

    @Before
    public void init() {
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    @Test
    public void testScan(){
        for(int i=0;i<100*100;i++){
            commonRedissionClient.setSingleKey("scanKey"+i,i,-1, TimeUnit.SECONDS);
        }
        //第一个0表示slot的起始坐标
        //在使用过程中使用scan 0 match scanKey* count 1000
        //match scanKey* 表示匹配的正则表达式
        //count 1000 表示一次性遍历的redis slot个数
        //redis采用的是一致性hash准则，先预先在有限的空间里面分配16384个slot
    }

    @After
    public void close() {
        //commonRedissionClient.close();
    }
}
