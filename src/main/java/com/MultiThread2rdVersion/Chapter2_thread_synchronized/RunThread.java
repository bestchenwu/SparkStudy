package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

public class RunThread extends Thread{

    private volatile boolean isRunning = true;

    public void setRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("begin run");
        //如果不加voltile,那么isRunning读取的是线程自带的内存里面的值
        //通过使用voltile,强迫从公共内存中读取值
        while(isRunning==true){

        }
        System.out.println("break run");
    }
}


