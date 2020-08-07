package com.MultiThread2rdVersion.Chapter3_thread_communicate.joinTest;

class JoinThread extends Thread {

    @Override
    public void run() {
        try {
            int sleepSeconds = (int) (100 * Math.random());
            System.out.println("sleep seconds:" + sleepSeconds);
            Thread.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
        JoinThread joinThread = new JoinThread();
        joinThread.start();
        joinThread.join();
        System.out.println("wait joinThread run finally");
    }
}
