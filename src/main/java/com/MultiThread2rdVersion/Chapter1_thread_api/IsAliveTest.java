package com.MultiThread2rdVersion.Chapter1_thread_api;

class IsAliveThread extends Thread{

    public IsAliveThread(){
        System.out.println("构造方法 this:"+getName());
        System.out.println("构造方法 this:"+this.isAlive());
        System.out.println("构造方法 CurrentThread:"+Thread.currentThread().getName());
        System.out.println("构造方法 CurrentThread:"+Thread.currentThread().isAlive());
    }

    @Override
    public void run() {
        System.out.println("run方法:"+getName());
        System.out.println("run方法:"+this.isAlive());
        System.out.println("run方法 CurrentThread:"+Thread.currentThread().getName());
        System.out.println("run方法 CurrentThread:"+Thread.currentThread().isAlive());
    }
}

public class IsAliveTest {

    public static void main(String[] args) {
        IsAliveThread myThread = new IsAliveThread();
        Thread thread = new Thread(myThread,"A");
        thread.start();
        //输出:
        //构造方法 this:thread-0
        //构造方法 this: false
        //构造方法 CurrentThread:main
        //构造方法 CurrentThread:true

        //run方法:thread-0
        //run方法:false
        //run方法 CurrentThread:A
        //run方法 CurrentThread:true
    }
}
