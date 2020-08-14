package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockService {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println("A");
            //释放锁,导致当前线程等到发信号或者interrupted
            condition.await();
            System.out.println("B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signal(){
        try{
            lock.lock();
            System.out.println("signal 时间为:"+System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}

class LockThread extends Thread {

    private LockService lockService;

    public LockThread(LockService lockService) {
        this.lockService = lockService;
    }

    @Override
    public void run() {
        lockService.await();
    }
}

public class LockAwaitTest {

    public static void main(String[] args) throws InterruptedException{
        LockService lockService = new LockService();
        LockThread lockThread = new LockThread(lockService);
        lockThread.start();
        Thread.sleep(1000l);
        lockService.signal();
    }
}
