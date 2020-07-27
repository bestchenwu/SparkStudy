package com.MultiThread2rdVersion.Chapter3_thread_communicate;

public class WaitTimeoutTest {

    public static void main(String[] args) {

        Runnable myRunnable = new Runnable() {

            private Object lock = new Object();

            @Override
            public void run() {
                try{
                    synchronized (lock){
                        System.out.println("lock wait start time:"+System.currentTimeMillis());
                        lock.wait(2*1000);
                        System.out.println("lock wait end time:"+System.currentTimeMillis());
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
