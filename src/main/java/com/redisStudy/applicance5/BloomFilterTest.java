package com.redisStudy.applicance5;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.redisson.api.RBloomFilter;

public class BloomFilterTest {

    private CommonRedissionClient commonRedissionClient;

    @Before
    public void init() {
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    @Test
    public void testBloomFilter(){
        RBloomFilter<Object> myBloom = commonRedissionClient.initBloomFilter("myBloom", 10000, 0.5);
        for(int i = 0;i<10000;i++){
            myBloom.add("abc"+i);
        }
        for(int j =0;j<10000;j+=2000){
            String value = "abc"+j;
            System.out.println(value+" is existsed :"+myBloom.contains(value));
        }
    }

    @After
    public void close() {
        commonRedissionClient.close();
    }
}
