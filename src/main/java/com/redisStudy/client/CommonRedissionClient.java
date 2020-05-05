package com.redisStudy.client;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 通用的redis java client
 * 使用{@link RedissonClient}
 */
public class CommonRedissionClient {

    public static CommonRedissionClient redissionClientInstance;
    private RedissonClient redissionClient;

    /**
     * 单机版redis的初始化方法
     *
     * @param redisHostAndPort
     */
    private CommonRedissionClient(String redisHostAndPort){
        Config config = new Config();
        config.useSingleServer().setAddress(redisHostAndPort);
        redissionClient = Redisson.create(config);

    }

    public static CommonRedissionClient getInstance(String redisPropertyFile){
        if(redissionClientInstance !=null){
            return redissionClientInstance;
        }
        synchronized(CommonRedissionClient.class){
            Properties props = new Properties();
            try{
                InputStream fis = CommonRedissionClient.class.getResourceAsStream("/"+redisPropertyFile);
                props.load(fis);
                redissionClientInstance = new CommonRedissionClient(props.getProperty("redis.host"));
            }catch(Exception e){
                throw new RuntimeException(e);
            }
            return redissionClientInstance;
        }
    }

    /**
     * 设置单个字符串格式的值
     *
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     * @author chenwu on 2020.5.5
     */
    public void setSingleKey(String key, String value, long expireTime, TimeUnit timeUnit){
        RBucket<Object> bucket = redissionClient.getBucket(key);
        bucket.set(value);
        if(expireTime>0){
            bucket.expire(expireTime,timeUnit);
        }
    }
}
