package com.redisStudy.client;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 通用的redis java client
 * 使用{@link RedissonClient}
 *
 * @author chenwu on 2020.5.5
 */
public class CommonRedissionClient implements Closeable {

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

    @Override
    public void close() {
        try{
            redissionClient.shutdown();
        }catch(Exception e){
            throw new RuntimeException(e);
        }

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
    public <T> void setSingleKey(String key, T value, long expireTime, TimeUnit timeUnit,Class<T> valueClass){
        RBucket<T> bucket = redissionClient.getBucket(key);
        bucket.set(value);
        if(expireTime>0){
            bucket.expire(expireTime,timeUnit);
        }
    }

    /**
     * 从单个key中取值
     *
     * @param key
     * @param <T>
     * @return T
     * @author chenwu on 2020.5.5
     */
    public <T> T getSingleKey(String key,Class<T> valueClass){
        RBucket<T> bucket = redissionClient.getBucket(key);
        if(bucket==null || !bucket.isExists() ){
            return null;
        }
        return bucket.get();
    }

    /**
     * hset key
     *
     * @param key
     * @param field
     * @param value
     * @param ttlTime
     * @param timeUnit
     * @author chenwu on 2020.5.5
     */
    public void hsetSingleKey(String key,String field,Object value,long ttlTime,TimeUnit timeUnit){
        RMap<String, Object> map = redissionClient.getMap(key);
        boolean isNewKey = false;
        if(map==null || map.isEmpty()){
            isNewKey = true;
        }
        map.put(field,value);
        if(isNewKey && ttlTime>0l){
            map.expire(ttlTime,timeUnit);
        }
    }

    /**
     * hget key
     *
     * @param key
     * @param field
     * @param valueClass
     * @param <T>
     * @return
     * @author chenwu on 2020.5.5
     */
    public <T> T hgetSingleKey(String key,String field,Class<T> valueClass){
        RMap<String, T> map = redissionClient.getMap(key);
        T value = map.get(field);
        return value;
    }
}
