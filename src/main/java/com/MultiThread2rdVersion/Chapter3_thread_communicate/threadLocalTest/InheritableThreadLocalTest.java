package com.MultiThread2rdVersion.Chapter3_thread_communicate.threadLocalTest;

class Tools{
    //public static InheritableThreadLocal t1 = new InheritableThreadLocal();
    public static InheritableThreadLocalExt t1 = new InheritableThreadLocalExt();
}

class ThreadA extends Thread{

    @Override
    public void run() {
        try{
            for(int i = 0;i<10;i++){
                System.out.println("在ThreadA线程中取值:"+Tools.t1.get());
                Thread.sleep(100);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class InheritableThreadLocalTest {

    public static void main(String[] args) {
        try{
            for(int i = 0;i<10;i++){
                if(Tools.t1.get()==null){
                    Tools.t1.set("main value+"+i);
                    Thread.sleep(100);
                }
            }
            Thread.sleep(5000);
            ThreadA threadA = new ThreadA();
            threadA.start();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
