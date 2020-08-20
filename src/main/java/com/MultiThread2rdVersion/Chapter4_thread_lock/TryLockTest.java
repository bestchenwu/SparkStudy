package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class LockService3 {

    private ReentrantLock lock = new ReentrantLock();

    public void service() throws InterruptedException {
        if (lock.tryLock(3, TimeUnit.SECONDS)) {
            System.out.println(Thread.currentThread().getName() + " get lock");
            Thread.sleep(10000);
        } else {
            System.out.println(Thread.currentThread().getName() + " not get lock");
        }
    }
}

class LockThread3 extends Thread {

    private LockService3 lockService3;

    public LockThread3(LockService3 lockService3) {
        this.lockService3 = lockService3;
    }

    @Override
    public void run() {
        try{
            lockService3.service();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}

public class TryLockTest {

    public static void main(String[] args) {
        LockService3 lockService3 = new LockService3();
        LockThread3 lockThread1 = new LockThread3(lockService3);
        lockThread1.setName("thread1");
        LockThread3 lockThread2 = new LockThread3(lockService3);
        lockThread2.setName("thread2");
        lockThread1.start();
        lockThread2.start();
    }
}
