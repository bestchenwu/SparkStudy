package com.MultiThread2rdVersion.Chapter3_thread_communicate;
import java.util.*;

class MyList{

    private volatile List<Object> myList = new ArrayList<>();

    public void add(){
        myList.add("TEST");
    }

    public int size(){
        return myList.size();
    }
}

class ThreadA extends Thread{
    private MyList myList;
    private Object lock;

    public ThreadA(MyList myList,Object lock){
        this.myList = myList;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            for(int i = 0;i<10;i++){
                myList.add();
                if(myList.size()==5){
                    System.out.println("send notfiy notice");
                    lock.notify();
                }
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }
}

class ThreadB extends Thread{

    private MyList myList;
    private Object lock;

    public ThreadB(MyList myList,Object lock){
        this.myList = myList;
        this.lock = lock;
    }

    @Override
    public void run() {
        try{
//            while(true){
//                if(myList.size()==5){
//                    System.out.println("size = 5,exists thread");
//                    throw new InterruptedException();
//                }
//            }
            //synchronized ()
            synchronized (lock){
                System.out.println("wait time begin:"+System.currentTimeMillis());
                if(myList.size()!=5){
                    lock.wait();
                }
                System.out.println("wait taime end:"+System.currentTimeMillis());
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}

public class TwoThreadTransDataTest {

    public static void main(String[] args) throws InterruptedException{
        MyList myList = new MyList();
        Object lock = new Object();
        ThreadA threadA = new ThreadA(myList,lock);
        ThreadB threadB = new ThreadB(myList,lock);

        threadB.start();
        Thread.sleep(1*1000);
        threadA.start();
    }
}
