package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线性打印
 *
 * @author chenwu on 2020.8.20
 */

class SequenceService{

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int nextMethodNumber = 1;

    public void testMethod1(){
        try{
            lock.lock();
            while(nextMethodNumber != 1){
                condition.await();
            }
            System.out.println("AAA");
            nextMethodNumber = 2;
            condition.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void testMethod2(){
        try{
            lock.lock();
            while(nextMethodNumber != 2){
                condition.await();
            }
            System.out.println("   BBB");
            nextMethodNumber = 3;
            condition.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void testMethod3(){
        try{
            lock.lock();
            while(nextMethodNumber != 3){
                condition.await();
            }
            System.out.println("        CCC");
            nextMethodNumber = 1;
            condition.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class Thread1 extends Thread{

    private SequenceService sequenceService;

    public Thread1(SequenceService sequenceService){
        this.sequenceService = sequenceService;
    }

    @Override
    public void run() {
        sequenceService.testMethod1();
    }
}

class Thread2 extends Thread{

    private SequenceService sequenceService;

    public Thread2(SequenceService sequenceService){
        this.sequenceService = sequenceService;
    }

    @Override
    public void run() {
        sequenceService.testMethod2();
    }
}

class Thread3 extends Thread{

    private SequenceService sequenceService;

    public Thread3(SequenceService sequenceService){
        this.sequenceService = sequenceService;
    }

    @Override
    public void run() {
        sequenceService.testMethod3();
    }
}

public class SequenceThreadTest {

    public static void main(String[] args) {
        SequenceService sequenceService = new SequenceService();
        Thread[] thread1Array = new Thread[5];
        Thread[] thread2Array = new Thread[5];
        Thread[] thread3Array = new Thread[5];
        for(int i = 0;i<5;i++){
            thread1Array[i] = new Thread1(sequenceService);
            thread2Array[i] = new Thread2(sequenceService);
            thread3Array[i] = new Thread3(sequenceService);
        }
        for(Thread thread:thread2Array){
            thread.start();
        }
        for(Thread thread:thread1Array){
            thread.start();
        }
        for(Thread thread:thread3Array){
            thread.start();
        }
    }
}
