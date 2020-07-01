package com.MultiThread2rdVersion.Chapter1_thread_api;
import java.util.*;

/**
 * 共享同一变量的多线程做法
 */
class MyThread extends Thread{

    private int count = 5;

    @Override
    synchronized public void run() {
        count --;
        System.out.println(Thread.currentThread().getName()+",count="+count);
    }
}
public class ShareSameVariableTest {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0;i<5;i++){
            Thread thread = new Thread(myThread,"thread"+i);
            threads.add(thread);
        }
        for(Thread thread:threads){
            thread.start();
        }
    }
}
