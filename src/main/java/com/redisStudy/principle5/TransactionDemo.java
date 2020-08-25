package com.redisStudy.principle5;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TransactionDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String user_id = "111";
        String key = keyFor(user_id);
        jedis.setnx(key, "5");
        multiPlayAccount(jedis, user_id);
        int result = Integer.parseInt(jedis.get(key));
        System.out.println("result=" + result);
    }

    private static void multiPlayAccount(Jedis jedis, String user_id) {
        String key = keyFor(user_id);
        while (true) {
            //redis的乐观锁,允许多个线程同时执行，但只有一个线程能修改成功
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2;
            Transaction multi = jedis.multi();
            multi.set(key, String.valueOf(value));
            List<Object> exec = multi.exec();
            if (exec != null) {
                break;
            }
        }
    }

    private static String keyFor(String user_id) {
        return String.format("account_%s", user_id);
    }
}
