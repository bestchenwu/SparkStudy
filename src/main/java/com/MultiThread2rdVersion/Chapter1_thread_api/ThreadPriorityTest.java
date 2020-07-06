package com.MultiThread2rdVersion.Chapter1_thread_api;

class ThreadA extends Thread{

    @Override
    public void run() {
        System.out.println("A priority:"+this.getPriority());
        ThreadB threadB = new ThreadB();
        threadB.start();
    }
}

class ThreadB extends ThreadA{

    @Override
    public void run() {
        System.out.println("B priority:"+this.getPriority());
    }
}

class ThreadC extends Thread{

    @Override
    public void run() {
        long count = 0;
        long startTime = System.currentTimeMillis();
        for(long i = 0;i<500*100*10;i++){
           count+=1;
        }
        System.out.println(this.getName()+" run time:"+(System.currentTimeMillis()-startTime));
    }
}

/**
 * 线程优先级测试
 *
 * @author chenwu on 2020.7.6
 */
public class ThreadPriorityTest {

    public static void main(String[] args) {
//        Thread.currentThread().setPriority(8);
//        ThreadA threadA = new ThreadA();
//        threadA.start();
        ThreadC threadC = new ThreadC();
        threadC.setName("threadC");
        ThreadC threadC1 = new ThreadC();
        threadC1.setName("threadC1");
        threadC1.setPriority(8);
        threadC.start();
        threadC1.start();
    }
}
