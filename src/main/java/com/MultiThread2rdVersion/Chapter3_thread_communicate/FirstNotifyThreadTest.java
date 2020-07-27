package com.MultiThread2rdVersion.Chapter3_thread_communicate;

public class FirstNotifyThreadTest {

    private static Object lock = new Object();

    private static Thread waitRunnable = new Thread() {
        @Override
        public void run() {
            try{
                synchronized (lock){
                    System.out.println("wait begin:"+System.currentTimeMillis());
                    lock.wait();
                    System.out.println("wait end:"+System.currentTimeMillis());
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    };

    private static Thread notifyRunnable = new Thread() {
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("notify begin:"+System.currentTimeMillis());
                lock.notify();
                System.out.println("notify end:"+System.currentTimeMillis());
            }
        }
    };

    public static void main(String[] args) {
        notifyRunnable.start();
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        waitRunnable.start();
    }
}
