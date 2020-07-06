package com.MultiThread2rdVersion.Chapter1_thread_api;

/**
 * 线程yield
 *
 * @author chenwu on 2020.7.6
 */
class YieldThread extends Thread{

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long count = 0;
        for(int i = 0;i<500*100;i++){
            //Thread.yield();
            //System.out.println("i="+i);
            count+=1;
        }
        System.out.println("run time :"+(System.currentTimeMillis()-startTime));
    }
}

public class YieldThreadTest {

    public static void main(String[] args) {
        YieldThread yieldThread = new YieldThread();
        yieldThread.start();
    }
}
