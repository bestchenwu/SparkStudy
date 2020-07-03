package com.MultiThread2rdVersion.Chapter1_thread_api;

import org.apache.spark.sql.sources.In;

class PrintThread extends Thread{

    private long i = 0;

    @Override
    public void run() {
        while(true){
            i++;
            System.out.println("i="+i);
        }
    }
}

public class SuspendThreadDeadLockTest2 {


    public static void main(String[] args) throws InterruptedException {
        PrintThread printThread = new PrintThread();
        printThread.start();
        Thread.sleep(1000);
        printThread.suspend();
        System.out.println("main end");
    }
}
