package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

import java.util.concurrent.atomic.AtomicInteger;

class AtmoicThread extends Thread{

    /** Unsafe.compareAndSwapInt **/
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {
        for(int i = 0;i<100;i++){
            System.out.println("count:"+counter.incrementAndGet());
        }
    }
}

public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtmoicThread atmoicService = new AtmoicThread();
        Thread thread1 = new Thread(atmoicService);
        Thread thread2 = new Thread(atmoicService);
        Thread thread3 = new Thread(atmoicService);
        Thread thread4 = new Thread(atmoicService);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
