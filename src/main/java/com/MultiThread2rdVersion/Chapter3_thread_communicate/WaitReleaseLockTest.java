package com.MultiThread2rdVersion.Chapter3_thread_communicate;

class WaitReleaseLockService{

    public void testMethod(Object lock){
        synchronized (lock){
            try{
                System.out.println("begin wait,threadName:"+Thread.currentThread().getName());
                lock.wait();
                //Thread.sleep(1*1000);
                System.out.println("end wait,threadName:"+Thread.currentThread().getName());
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class WaitReleaseLockThread extends Thread{

    private Object lock;

    public WaitReleaseLockThread(Object lock,String threadName){
        super(threadName);
        this.lock = lock;
    }

    @Override
    public void run() {
        WaitReleaseLockService service = new WaitReleaseLockService();
        service.testMethod(lock);
    }
}

public class WaitReleaseLockTest {

    public static void main(String[] args) {
        Object lock = new Object();
        WaitReleaseLockThread thread1 = new WaitReleaseLockThread(lock,"thread1");
        //WaitReleaseLockThread thread2 = new WaitReleaseLockThread(lock,"thread2");
        thread1.start();
        try{
            Thread.sleep(2*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        //对处于
        thread1.interrupt();
        //thread2.start();

    }
}
