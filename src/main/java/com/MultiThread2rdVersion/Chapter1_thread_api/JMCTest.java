package com.MultiThread2rdVersion.Chapter1_thread_api;

/**
 * 先后起5个线程,同时使用jmc命令来查看
 */
public class JMCTest {

    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread("thread"+i){

                @Override
                public void run() {
                    try{
                        Thread.sleep(5*1000*60);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
