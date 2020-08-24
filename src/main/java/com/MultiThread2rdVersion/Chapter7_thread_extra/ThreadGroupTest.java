package com.MultiThread2rdVersion.Chapter7_thread_extra;

class GroupThread extends Thread{

    @Override
    public void run() {
        try{
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("threadName:"+Thread.currentThread().getName());
                Thread.sleep(3000);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class ThreadGroupTest {

    public static void main(String[] args) {
        GroupThread aRunnable = new GroupThread();
        GroupThread bRunnable = new GroupThread();
        ThreadGroup threadGroup = new ThreadGroup("test group");
        Thread aThread = new Thread(threadGroup,aRunnable);
        Thread bThread = new Thread(threadGroup,bRunnable);
        aThread.start();
        bThread.start();
        System.out.println("活动线程数:"+threadGroup.activeCount());
        System.out.println("线程的名称:"+threadGroup.getName());
    }
}
