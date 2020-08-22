package com.redisStudy.appliance7;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Redission令牌限流器<br/>
 * 测试{@link com.redisStudy.client.CommonRedissionClient#getRateLimiter(String, int, int, RateIntervalUnit)}
 *
 * @author chenwu on 2020.8.2
 */
public class RateLimiterTest {

    private CommonRedissionClient commonRedissionClient;

    @Before
    public void init() {
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    @Test
    public void testRateLimiter() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        //每隔10秒最多允许1个访问
        final RRateLimiter rateLimiter = commonRedissionClient.getRateLimiter("raoshanshan", 1, 10, RateIntervalUnit.SECONDS);
        for (int i = 0; i < 50; i++) {
            final int j = i;
            executorService.submit(() -> {
                try{
                    if(rateLimiter.tryAcquire(1)){
                        System.out.println("thread:" + j + "begin run,time:"+System.currentTimeMillis());
                    }
                    //System.out.println("thread:" + j + "begin run,time:"+System.currentTimeMillis());
                }catch(Exception e){
                    e.printStackTrace();
                }

            });
        }
    }

    @After
    public void close() {
        //commonRedissionClient.close();
    }
}
