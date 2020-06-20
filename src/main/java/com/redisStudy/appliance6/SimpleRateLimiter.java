package com.redisStudy.appliance6;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.api.RScoredSortedSet;

import java.util.concurrent.TimeUnit;

/**
 * 实现简单断流 固定的一段时间内只允许访问多少次
 * 参考文献:https://blog.csdn.net/qq_44762698/article/details/88421455或者
 * https://blog.csdn.net/lmx125254/article/details/90700118
 */
public class SimpleRateLimiter {

    private CommonRedissionClient commonRedissionClient;

    @Before
    public void init() {
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    @Test
    public void testLimiter(){
        //对于同一个用户,在10秒内只允许有3次访问
        long period = 10l;
        int limit = 3;
        RScoredSortedSet<String> sortedSet = commonRedissionClient.getSortedSet("user1", period+1, TimeUnit.SECONDS);
        for(int i = 0;i<60;i++){
            long currentTimeMillis = System.currentTimeMillis();
            sortedSet.removeRangeByScore(0,true,currentTimeMillis-period*1000,true);
            if(sortedSet.size()>limit){
                //表明达到限流了
                System.out.println("too many count:"+sortedSet.size());
            }else{
                System.out.println("count is ok :"+sortedSet.size());
                sortedSet.add(currentTimeMillis,""+currentTimeMillis);
            }
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    @After
    public void close() {
        commonRedissionClient.close();
    }
}
