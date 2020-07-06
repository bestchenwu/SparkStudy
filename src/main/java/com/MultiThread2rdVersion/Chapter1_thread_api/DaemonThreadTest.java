package com.MultiThread2rdVersion.Chapter1_thread_api;

class DaemonThread extends Thread {

    @Override
    public void run(){
        for (int i = 0; i < 500 * 100 * 10; i++) {
            System.out.println("i=" + i);
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}

public class DaemonThreadTest {

    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        //设置守护线程,必须在start之前执行
        //daemonThread.setDaemon(true);
        Thread.sleep(1000 * 2);
    }
}
