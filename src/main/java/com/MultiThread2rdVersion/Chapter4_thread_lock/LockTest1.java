package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.atomic.AtomicInteger;

class ThreadA extends Thread{

    private MyService myService;

    public ThreadA(MyService myService,String threadName){
        super(threadName);
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethodA();
    }
}

class ThreadB extends Thread{

    private MyService myService;

    public ThreadB(MyService myService,String threadName){
        super(threadName);
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethodB();
    }
}

public class LockTest1 {

    public static void main(String[] args) {
        MyService myService = new MyService();
        AtomicInteger idGenerator = new AtomicInteger(0);
        ThreadA threadA = new ThreadA(myService,"thread:"+idGenerator.getAndIncrement());
        ThreadB threadB = new ThreadB(myService,"thread:"+idGenerator.getAndIncrement());
        ThreadA threadA1 = new ThreadA(myService,"thread:"+idGenerator.getAndIncrement());
        ThreadB threadB1 = new ThreadB(myService,"thread:"+idGenerator.getAndIncrement());
        threadA.start();
        threadB.start();
        threadA1.start();
        threadB1.start();
    }
}
