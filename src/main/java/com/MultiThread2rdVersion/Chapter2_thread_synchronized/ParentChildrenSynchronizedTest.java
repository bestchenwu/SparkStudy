package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

/**
 * 父子线程可重入
 *
 * @author chenwu on 2020.7.6
 */

class Main{

    protected int i = 10;

    public void operateIByMain(){
        i+=1;
        System.out.println("main i="+i);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class Sub extends Main{

    public void operateIBySub(){
        while(true){
            i-=1;
            System.out.println("sub i="+i);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            super.operateIByMain();
        }
    }
}

class SubThread extends Thread{
    Sub sub = new Sub();

    @Override
    public void run() {
        sub.operateIBySub();
    }
}

public class ParentChildrenSynchronizedTest {

    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.start();
    }
}
