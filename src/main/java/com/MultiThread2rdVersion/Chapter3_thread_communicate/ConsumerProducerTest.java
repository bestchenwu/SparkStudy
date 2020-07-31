package com.MultiThread2rdVersion.Chapter3_thread_communicate;

import java.util.ArrayList;
import java.util.List;

class Box {

    public static volatile int poolSize = 0;
    public static final int MAX_POOL_SIZE = 10;
}

class ConsumerProducerService {

    private Object lock = new Object();

    public void consume() {
        try {
            synchronized (lock) {
                while (Box.poolSize <= 0) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " consume one");
                Box.poolSize -= 1;
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void produce() {
        try {
            synchronized (lock) {
                while (Box.poolSize >= Box.MAX_POOL_SIZE) {
                    lock.wait();
                }
                System.out.println(Thread.currentThread().getName() + " produce one");
                Box.poolSize += 1;
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ConsumeThread extends Thread{

    private ConsumerProducerService service;

    public ConsumeThread(ConsumerProducerService service,String threadName){
        super(threadName);
        this.service = service;
    }

    @Override
    public void run() {
        while(true){
            service.consume();
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}

class ProduceThread extends Thread{

    private ConsumerProducerService service;

    public ProduceThread(ConsumerProducerService service,String threadName){
        super(threadName);
        this.service = service;
    }

    @Override
    public void run() {
        while(true){
            service.produce();
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}

public class ConsumerProducerTest {

    public static void main(String[] args) {
        List<Thread> consumerThreadList = new ArrayList<>();
        List<Thread> producerThreadList = new ArrayList<>();
        ConsumerProducerService service = new ConsumerProducerService();
        for(int i = 0;i<5;i++){
            ProduceThread produceThread = new ProduceThread(service,"Producethread-"+i);
            producerThreadList.add(produceThread);
        }
        for(int j=0;j<3;j++){
            ConsumeThread consumeThread = new ConsumeThread(service,"ConsumeThread-"+j);
            consumerThreadList.add(consumeThread);
        }
        producerThreadList.stream().forEach(Thread::start);
        consumerThreadList.stream().forEach(Thread::start);
    }
}
