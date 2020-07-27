package com.MultiThread2rdVersion.Chapter3_thread_communicate;

class Box{

    public static volatile Integer poolSize = 0;
}

class ConsumerProducerService{

    private Object lock = new Object();

    public void consume(){
        try{
            synchronized (lock){
                while(Box.poolSize<=0){
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName()+" consume one");
                Box.poolSize -=1;
                lock.notifyAll();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void produce(){

    }
}

public class ConsumerProducerTest {
}
