package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

class DeadThread implements Runnable{

    private String userName;

    private Object object1 = new Object();
    private Object object2 = new Object();

    public void setFlag(String userName){
        this.userName = userName;
    }

    @Override
    public void run() {
        if(userName.equals("a")){
            synchronized (object1){
                System.out.println("a thread begin");
                try{
                    Thread.sleep(3*1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (object2){
                    System.out.println("lock object1->object2");
                }
            }
        }else{
            synchronized (object2){
                System.out.println("b thread begin");
                try{
                    Thread.sleep(3*1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (object1){
                    System.out.println("lock object2->object1");
                }
            }
        }
    }
}

public class DeadThreadTest {

    public static void main(String[] args) {
        DeadThread deadThread = new DeadThread();
        Thread thread1 = new Thread(deadThread);
        thread1.setName("thread1");
        deadThread.setFlag("a");
        thread1.start();
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        Thread thread2 = new Thread(deadThread);
        thread2.setName("thread2");
        deadThread.setFlag("b");
        thread2.start();
        try{
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
