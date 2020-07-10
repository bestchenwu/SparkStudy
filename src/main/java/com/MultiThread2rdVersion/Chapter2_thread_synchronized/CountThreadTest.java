package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

class CountThread extends Thread{
    private static int count = 0;

    //加上static 后相当于对CountThread.class进行加锁
    //这个时候加不加voltile都可以
    synchronized static public void addCount(){
        for(int i=0;i<100;i++){
            count++;
            //在多线程的情况下count++并不是原子性的
        }
        System.out.println("count="+count);
    }

    @Override
    public void run() {
        addCount();
    }
}

public class CountThreadTest {

    public static void main(String[] args) {
        for(int j = 0;j<100;j++){
            CountThread countThread = new CountThread();
            countThread.start();
        }
    }
}
