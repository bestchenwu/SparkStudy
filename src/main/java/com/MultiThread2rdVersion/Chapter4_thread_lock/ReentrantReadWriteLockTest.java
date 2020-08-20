package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁(读的时候可共享锁,写入的时候互斥)
 *
 * @author chenwu on 2020.8.20
 */
class ReadWriteService {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println("begin write " + Thread.currentThread().getName() + " at :" + System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println("end write " + Thread.currentThread().getName() + " at :" + System.currentTimeMillis());
            lock.writeLock().unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println("begin read " + Thread.currentThread().getName() + " at :" + System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println("end read " + Thread.currentThread().getName() + " at :" + System.currentTimeMillis());
            lock.readLock().unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WriteThread extends Thread {

    private ReadWriteService readWriteService;

    public WriteThread(ReadWriteService readWriteService) {
        this.readWriteService = readWriteService;
    }

    @Override
    public void run() {
        readWriteService.write();
    }
}


class ReadThread extends Thread {

    private ReadWriteService readWriteService;

    public ReadThread(ReadWriteService readWriteService) {
        this.readWriteService = readWriteService;
    }

    @Override
    public void run() {
        readWriteService.read();
    }
}

public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        ReadWriteService readWriteService = new ReadWriteService();
        WriteThread writeThread = new WriteThread(readWriteService);
        writeThread.setName("writeThread");

        ReadThread readThread = new ReadThread(readWriteService);
        readThread.setName("readThread");
        writeThread.start();
        readThread.start();
//        ReadWriteThread[] threadArray = new ReadWriteThread[5];
//        for (int i = 0; i < 5; i++) {
//            ReadWriteThread thread = new ReadWriteThread(readWriteService);
//            thread.setName("thread" + i);
//            threadArray[i] = thread;
//        }
//        for (ReadWriteThread thread : threadArray) {
//            thread.start();
//        }
    }
}
