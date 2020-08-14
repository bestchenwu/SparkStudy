package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService{

    private Lock lock = new ReentrantLock();

    public void testMethodA(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"  begin methodA ,time="+System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"  end methodA ,time="+System.currentTimeMillis());
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void testMethodB(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"  begin methodB ,time="+System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"  end methodB ,time="+System.currentTimeMillis());
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class MyThread extends Thread{

    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private MyService myService;

    public MyThread(MyService myService){
        super("thread"+idGenerator.getAndIncrement());
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethodA();
    }
}

public class LockTest {

    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread myThread1 = new MyThread(myService);
        MyThread myThread2 = new MyThread(myService);
        MyThread myThread3 = new MyThread(myService);
        MyThread myThread4 = new MyThread(myService);
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
    }
}
