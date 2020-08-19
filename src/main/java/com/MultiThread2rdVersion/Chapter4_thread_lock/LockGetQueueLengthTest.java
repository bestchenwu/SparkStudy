package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockService2{

    private ReentrantLock lock = new ReentrantLock();

    public void service(){
        try{
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }
}

class LockThread2 extends Thread{

    private LockService2 lockService2 ;

    public LockThread2(LockService2 lockService2 ){
        this.lockService2 = lockService2;
    }

    @Override
    public void run() {
        lockService2.service();
    }
}

public class LockGetQueueLengthTest {

    public static void main(String[] args) throws InterruptedException {
        LockService2 lockService2 = new LockService2();
        Thread[] threadArray = new Thread[10];
        for(int i = 0;i<10;i++){
            threadArray[i] = new LockThread2(lockService2);
        }
        for(int i = 0;i<10;i++){
            threadArray[i].start();
        }
        Thread.sleep(1000);
        System.out.println("queue length:"+lockService2.getLock().getQueueLength());
    }
}
