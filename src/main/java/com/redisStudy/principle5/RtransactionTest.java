package com.redisStudy.principle5;

import com.redisStudy.client.CommonRedissionClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.redisson.api.RSet;
import org.redisson.api.RTransaction;

/**
 * 使用{@link CommonRedissionClient}来构建事务
 *
 * @author chenwu on 2020.8.25
 */
public class RtransactionTest {

    private CommonRedissionClient commonRedissionClient;

    @Before
    public void init() {
        commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");
    }

    @Test
    public void testRtransaction(){
        RTransaction rTransaction = commonRedissionClient.getRTransaction();
        RSet<String> testRedissionSet = rTransaction.getSet("testRedissionSet1");
        try{
            for(int i = 0;i<10;i++){
                if(i ==2){
                    throw new IllegalStateException("i = 2");
                }
                testRedissionSet.add("sweet_"+i);
            }
            rTransaction.commit();
        }catch(Exception e){
            e.printStackTrace();
            rTransaction.rollback();
        }
        RSet<String> result = commonRedissionClient.getRSet("testRedissionSet1");
        //RSet<String> result = rTransaction.getSet("testRedissionSet");
        Assert.assertEquals(0,result.size());
    }

    @After
    public void close() {
        //commonRedissionClient.close();
    }
}
