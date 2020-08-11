package com.MultiThread2rdVersion.Chapter3_thread_communicate.joinTest;

class ThreadA extends Thread{

    private ThreadB threadB;

    public ThreadA(ThreadB threadB){
        this.threadB = threadB;
    }

    @Override
    public void run() {
        try{
            synchronized (threadB){
                System.out.println("begin A "+System.currentTimeMillis());
                Thread.sleep(500);
                System.out.println("end A "+System.currentTimeMillis());
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread{

    @Override
    synchronized public void run() {
        try{
            System.out.println("begin B "+System.currentTimeMillis());
            Thread.sleep(500);
            System.out.println("end B "+System.currentTimeMillis());
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class Run1 {

    public static void main(String[] args) {
        try{
            ThreadB threadB = new ThreadB();
            ThreadA threadA = new ThreadA(threadB);
            threadA.start();
            threadB.start();
            threadB.join(200);
            System.out.println("main end:"+System.currentTimeMillis());
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
