package com.MultiThread2rdVersion.Chapter1_thread_api;

class StopThread extends Thread{

    private int i = 0;

    @Override
    public void run() {
//        while(true){
//            System.out.println("i="+i++);
//            try{
//                Thread.sleep(100);
//            }catch(InterruptedException e){
//                e.printStackTrace();
//            }
//        }
        try{
            this.stop();
        }catch(ThreadDeath e){
            e.printStackTrace();
        }

    }
}

public class ThreadStopTest {

    public static void main(String[] args) {
        StopThread stopThread = new StopThread();
        try{
            stopThread.start();
            Thread.sleep(1000);
            //stopThread.stop();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
