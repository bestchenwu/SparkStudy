package com.MultiThread2rdVersion.Chapter1_thread_api;

import org.apache.spark.sql.sources.In;

class SynchronizedObject{

    synchronized public void printString(){
        System.out.println("begin:"+Thread.currentThread().getName());
        if(Thread.currentThread().getName().equals("a")){
            System.out.println("a线程永远的suspend");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }
}
public class SleepThreadDeadLockTest {

    public static void main(String[] args) throws InterruptedException {
        final SynchronizedObject object = new SynchronizedObject();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                object.printString();
            }
        };
        thread1.setName("a");
        thread1.start();
        Thread.sleep(1000);
        Thread thread2 =new Thread(){

            @Override
            public void run() {
                System.out.println("因为printString被a线程锁定了,所以打印不了");
                //这种情况下printString被一直锁住
                object.printString();
            }
        };
        thread2.start();
    }
}
