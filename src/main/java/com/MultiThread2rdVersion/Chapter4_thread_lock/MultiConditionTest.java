package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockService1{

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void testMethodA(){
        try{
            lock.lock();
            System.out.println("conditionA await startTime:"+System.currentTimeMillis());
            conditionA.await();
            System.out.println("conditionA await endTime:"+System.currentTimeMillis());
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void testMethodB(){
        try{
            lock.lock();
            System.out.println("conditionB await startTime:"+System.currentTimeMillis());
            conditionB.await();
            System.out.println("conditionB await endTime:"+System.currentTimeMillis());
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signalA(){
        try{
            lock.lock();
            conditionA.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void signalB(){
        try{
            lock.lock();
            conditionB.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

class LockThreadA extends Thread{

    public LockService1 lockService1;

    public LockThreadA(LockService1 lockService1){
        this.lockService1 = lockService1;
    }

    @Override
    public void run() {
        lockService1.testMethodA();
    }
}

class LockThreadB extends Thread{

    public LockService1 lockService1;

    public LockThreadB(LockService1 lockService1){
        this.lockService1 = lockService1;
    }

    @Override
    public void run() {
        lockService1.testMethodB();
    }
}

public class MultiConditionTest {

    public static void main(String[] args) throws InterruptedException{
        LockService1 lockService1 = new LockService1();
        LockThreadA lockThreadA = new LockThreadA(lockService1);
        LockThreadB lockThreadB = new LockThreadB(lockService1);
        lockThreadA.start();
        lockThreadB.start();
        Thread.sleep(2000);
        lockService1.signalA();
    }
}
