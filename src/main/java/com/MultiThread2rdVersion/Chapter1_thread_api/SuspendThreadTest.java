package com.MultiThread2rdVersion.Chapter1_thread_api;

/**
 * 线程的suspend 测试
 *
 * @author chenwu on 2020.7.3
 */
class SuspendThread extends Thread{

    private long i = 0;

    public long getI(){
        return i;
    }

    @Override
    public void run() {
        while(true){
            i++;
        }
    }
}

public class SuspendThreadTest {

    public static void main(String[] args) throws InterruptedException{
        SuspendThread thread = new SuspendThread();
        thread.start();
        //A段
        Thread.sleep(1000);
        thread.suspend();
        System.out.println("A time:"+System.currentTimeMillis()+",i="+thread.getI());
        Thread.sleep(1000);
        System.out.println("A time:"+System.currentTimeMillis()+",i="+thread.getI());
        thread.resume();
        //B端
        Thread.sleep(5000);
        thread.suspend();
        System.out.println("B time:"+System.currentTimeMillis()+",i="+thread.getI());
        Thread.sleep(1000);
        System.out.println("B time:"+System.currentTimeMillis()+",i="+thread.getI());
    }
}
