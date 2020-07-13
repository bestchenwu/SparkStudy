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

    public ThreadA(MyList myList){
        this.myList = myList;
    }

    @Override
    public void run() {
        for(int i = 0;i<10;i++){
            myList.add();
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class ThreadB extends Thread{

    private MyList myList;

    public ThreadB(MyList myList){
        this.myList = myList;
    }

    @Override
    public void run() {
        try{
            while(true){
                if(myList.size()==5){
                    System.out.println("size = 5,exists thread");
                    throw new InterruptedException();
                }
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}

public class TwoThreadTransDataTest {

    public static void main(String[] args) {
        MyList myList = new MyList();
        ThreadA threadA = new ThreadA(myList);
        ThreadB threadB = new ThreadB(myList);
        threadA.start();
        threadB.start();
    }
}
