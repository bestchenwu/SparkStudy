package com.MultiThread2rdVersion.Chapter1_thread_api;

class ThreadNameThread extends Thread{

    public ThreadNameThread(){
        System.out.println("构造方法:"+getName());
        System.out.println("构造方法 CurrentThread:"+Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run方法:"+getName());
        System.out.println("run方法 CurrentThread:"+Thread.currentThread().getName());
    }
}

public class ThreadGetNameTest {

    public static void main(String[] args) {
        ThreadNameThread myThread = new ThreadNameThread();
        //这里的A是给Thread赋予一个名称
        Thread thread = new Thread(myThread,"A");
        thread.start();
        //输出:
        //构造方法:Thread-0 刚开始随机赋值一个名称
        //构造方法 current:main
        //run方法:Thread-0
        //run方法 current:A
    }
}
