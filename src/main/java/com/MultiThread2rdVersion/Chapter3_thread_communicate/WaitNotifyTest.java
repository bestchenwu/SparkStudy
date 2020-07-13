package com.MultiThread2rdVersion.Chapter3_thread_communicate;

class MyThread1 extends Thread{

    private Object lock;

    public MyThread1(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("wait time begin:"+System.currentTimeMillis());
            try{
                lock.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("wait time end:"+System.currentTimeMillis());
        }
    }
}

class MyThread2 extends Thread{

    private Object lock;

    public MyThread2(Object lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("notify time begin:"+System.currentTimeMillis());
            lock.notify();
            System.out.println("notify time end:"+System.currentTimeMillis());
        }
    }
}

public class WaitNotifyTest {

    public static void main(String[] args) {
        Object lock = new Object();
        MyThread1 thread1 = new MyThread1(lock);
        MyThread2 thread2 = new MyThread2(lock);
        thread1.start();
        try{
            Thread.sleep(2*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        thread2.start();
    }
}
