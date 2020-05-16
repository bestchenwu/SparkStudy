package com.redisStudy.client;

import org.redisson.Redisson;
import org.redisson.api.*;
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
    private CommonRedissionClient(String redisHostAndPort) {
        Config config = new Config();
        config.useSingleServer().setAddress(redisHostAndPort);
        redissionClient = Redisson.create(config);
    }

    @Override
    public void close() {
        try {
            redissionClient.shutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static CommonRedissionClient getInstance(String redisPropertyFile) {
        if (redissionClientInstance != null) {
            return redissionClientInstance;
        }
        synchronized (CommonRedissionClient.class) {
            Properties props = new Properties();
            try {
                InputStream fis = CommonRedissionClient.class.getResourceAsStream("/" + redisPropertyFile);
                props.load(fis);
                redissionClientInstance = new CommonRedissionClient(props.getProperty("redis.host"));
            } catch (Exception e) {
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
    public <T> void setSingleKey(String key, T value, long expireTime, TimeUnit timeUnit, Class<T> valueClass) {
        RBucket<T> bucket = redissionClient.getBucket(key);
        bucket.set(value);
        if (expireTime > 0) {
            bucket.expire(expireTime, timeUnit);
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
    public <T> T getSingleKey(String key, Class<T> valueClass) {
        RBucket<T> bucket = redissionClient.getBucket(key);
        if (bucket == null || !bucket.isExists()) {
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
    public void hsetSingleKey(String key, String field, Object value, long ttlTime, TimeUnit timeUnit) {
        RMap<String, Object> map = redissionClient.getMap(key);
        boolean isNewKey = false;
        if (map == null || map.isEmpty()) {
            isNewKey = true;
        }
        map.put(field, value);
        if (isNewKey && ttlTime > 0l) {
            map.expire(ttlTime, timeUnit);
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
    public <T> T hgetSingleKey(String key, String field, Class<T> valueClass) {
        RMap<String, T> map = redissionClient.getMap(key);
        T value = map.get(field);
        return value;
    }

    /**
     * 以原子的方式设置值
     *
     * @param key
     * @param value
     * @param valueClass
     * @param ttlTime
     * @param timeUnit
     * @param <T>
     * @param lockName
     * @return boolean
     * @author chenwu on 2020.5.6
     */
    public <T> boolean setNx(String key, T value, Class<T> valueClass, long ttlTime, TimeUnit timeUnit, String lockName) {
        if (ttlTime <= 0) {
            throw new IllegalArgumentException("ttltime should be more than 0");
        }
        RLock lock = redissionClient.getLock(lockName);
        boolean isLock ;
        try {
            //尝试加锁，最多等待ttlTime,并且在10秒以后自动释放锁
            isLock = lock.tryLock(ttlTime, 10,timeUnit);
            if(isLock){
                RBucket<T> bucket = redissionClient.getBucket(key);
                bucket.set(value);
                //Thread.sleep(10*1000);
            }else{
                System.out.println("can't get lock");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return true;
    }

    /**
     * 往指定key的双端队列里添加元素
     *
     * @param key
     * @param value
     * @param ttlTime
     * @param timeUnit
     * @param <T>
     * @author chenwu on 2020.5.13
     */
    public <T> void addDeque(String key,T value,long ttlTime, TimeUnit timeUnit){
        RDeque<T> deque = redissionClient.getDeque(key);
        deque.addLast(value);
        if(ttlTime>0){
            deque.expire(ttlTime,timeUnit);
        }
    }

    /**
     * 从指定key的双端队列里取处元素
     *
     * @param key
     * @param pollFirst 是否从头部取元素
     * @param valueClass
     * @param <T>
     * @return T
     * @author chenwu on 2020.5.13
     */
    public <T> T pollDeque(String key,boolean pollFirst,Class<T> valueClass){
        RDeque<T> deque = redissionClient.getDeque(key);
        T result;
        if(pollFirst){
            result = deque.pollFirst();
        }else{
            result = deque.pollLast();
        }
        return result;
    }

    /**
     * 往阻塞队列里添加元素
     *
     * @param key
     * @param value
     * @param valueClass
     * @param ttlTime
     * @param timeUnit
     * @param <T>
     * @author chenwu on 2020.5.14
     */
    public <T> void addBlockQueue(String key,T value,Class<T> valueClass,long ttlTime, TimeUnit timeUnit){
        RBlockingDeque<T> blockingDeque = redissionClient.getBlockingDeque(key);
        blockingDeque.offer(value);
        if(ttlTime>0){
            blockingDeque.expire(ttlTime,timeUnit);
        }
    }

    /**
     * 从阻塞队列里删除元素
     *
     * @param key
     * @param <T>
     * @return T
     * @author chenwu on 2020.5.14
     */
    public <T> T pollBlockQueue(String key,long waitTime,TimeUnit timeUnit) throws InterruptedException{
        RBlockingDeque<T> blockingDeque = redissionClient.getBlockingDeque(key);
        T object = blockingDeque.poll(waitTime,timeUnit);
        return object;
    }

    /**
     * 使用HyperLogLog 基数估算法
     *
     * @param key
     * @param value
     * @param valueClass
     * @param <T>
     * @return long
     * @author chenwu on 2020.5.16
     */
    public <T> long addHyperLog(String key,T value,Class<T> valueClass,long ttlTime,TimeUnit timeUnit){
        RHyperLogLog<T> hyperLogLog = redissionClient.getHyperLogLog(key);
        if(ttlTime>0){
            hyperLogLog.expire(ttlTime,timeUnit);
        }
        hyperLogLog.add(value);
        return hyperLogLog.count();
    }
}
