package com.MultiThread2rdVersion.Chapter1_thread_api;

class ReturnThread extends Thread{

    @Override
    public void run() {
        while(true){
            if(this.isInterrupted()){
                System.out.println("interrupted");
                return;
            }
        }
    }
}

public class ThreadAndReturnTest {

    public static void main(String[] args) throws InterruptedException {
        ReturnThread t = new ReturnThread();
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
