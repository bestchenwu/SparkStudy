package com.redisStudy.appliance1;

import com.redisStudy.client.CommonRedissionClient;

import java.util.concurrent.TimeUnit;

public class SetNxThread extends Thread {

    private CommonRedissionClient redissionClient;
    private String key;
    private Integer value;
    private String lockName;

    public SetNxThread(CommonRedissionClient redissionClient,String key, Integer value){
        this.redissionClient = redissionClient;
        this.key = key;
        this.value = value;
        lockName = "testLock";
    }

    @Override
    public void run() {
        boolean result = redissionClient.setNx(key,value,Integer.class,100l, TimeUnit.SECONDS,lockName);
        if(result){
            System.out.println("set value:"+value+" success");
        }else{
            System.out.println("set value:"+value+" failure");
        }
    }
}
