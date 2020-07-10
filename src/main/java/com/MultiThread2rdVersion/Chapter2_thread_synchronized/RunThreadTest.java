package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

public class RunThreadTest {

    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(100);
        runThread.setRunning(false);
        System.out.println("is running is false");
    }
}
