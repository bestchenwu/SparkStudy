package com.paralleProgrammingStudy.UpDownThreadSwap;

public class DeadLockTest {

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        String A = "A";
        String B = "B";
        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                synchronized (A){
                    try{
                        Thread.sleep(2000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("AB");
                    }
                }
            }
        };
        Thread thread2 = new Thread("thread2") {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("BA");
                    }
                }
            }
        };
        thread1.start();
        thread2.start();
    }
}
