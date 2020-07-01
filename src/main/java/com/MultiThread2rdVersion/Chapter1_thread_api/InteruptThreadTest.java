package com.MultiThread2rdVersion.Chapter1_thread_api;

class MyInterupteThread extends Thread{

    @Override
    public void run() {
        for(int i = 0;i<=50*1000;i++){
            System.out.println("i="+i);
        }
    }
}

public class InteruptThreadTest {

    public static void main(String[] args) throws InterruptedException {
        MyInterupteThread myThread = new MyInterupteThread();
        myThread.start();
        Thread.sleep(1*1000);
        myThread.interrupt();
        System.out.println("zzzzzzz");
    }
}
