package com.MultiThread2rdVersion.Chapter3_thread_communicate;

/**
 * Wait notify Service
 *
 * @author chenwu on 2020.7.26
 */
class WaitAndNotifyServic{

    private Object lock = new Object();

    public void waitMethod(){
        try{
            synchronized (lock){
                System.out.println("wait method begin:"+Thread.currentThread().getName());
                lock.wait();
                System.out.println("wait method end:"+Thread.currentThread().getName());
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void notifyMethod(){
        synchronized (lock){
            System.out.println("notify method begin:"+Thread.currentThread().getName());
            lock.notify();
            System.out.println("notify method end:"+Thread.currentThread().getName());
        }
    }

    public void notifyAllMethod(){
        synchronized (lock){
            System.out.println("notifyAll method begin:"+Thread.currentThread().getName());
            lock.notifyAll();
            System.out.println("notifyAll method end:"+Thread.currentThread().getName());
        }
    }

}

class WaitThread extends Thread{

    private WaitAndNotifyServic servic;

    public WaitThread(WaitAndNotifyServic servic,String threadName){
        super(threadName);
        this.servic = servic;
    }

    @Override
    public void run() {
        servic.waitMethod();
    }
}

class NotifyThread extends Thread{

    private WaitAndNotifyServic servic;

    public NotifyThread(WaitAndNotifyServic servic,String threadName){
        super(threadName);
        this.servic = servic;
    }

    @Override
    public void run() {
        servic.notifyMethod();
    }
}

class NotifyAllThread extends Thread{

    private WaitAndNotifyServic servic;

    public NotifyAllThread(WaitAndNotifyServic servic,String threadName){
        super(threadName);
        this.servic = servic;
    }

    @Override
    public void run() {
        //notifyAll会按照线程睡眠的顺序依次倒序唤醒
        servic.notifyAllMethod();
    }
}

public class WaitAndNotifyServiceTest {

    public static void main(String[] args) {
        WaitAndNotifyServic servic = new WaitAndNotifyServic();
        for(int i = 0;i<10;i++){
            WaitThread waitThread = new WaitThread(servic,"waitThread-"+i);
            waitThread.start();
        }
        try{
            Thread.sleep(2*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        NotifyAllThread notifyAllThread = new NotifyAllThread(servic,"notifyAllThread");
        notifyAllThread.start();
//        int j = 0;
//        NotifyThread notifyThread = new NotifyThread(servic,"notifyThread-"+(j++));
//        notifyThread.start();
//        NotifyThread notifyThread1 = new NotifyThread(servic,"notifyThread-"+(j++));
//        notifyThread1.start();
//        NotifyThread notifyThread2 = new NotifyThread(servic,"notifyThread-"+(j++));
//        notifyThread2.start();
    }
}
