package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁 与 非公平锁
 *
 * @author chenwu on 2020.8.17
 */
class FairNonFairLockService {

    private Lock lock;

    public FairNonFairLockService(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void testMethod() {
        try {
            lock.lock();
            System.out.println("testMethod name " + Thread.currentThread().getName());
            Thread.sleep(500);
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
}

class FairNonFairLockThread extends Thread {

    private FairNonFairLockService service;

    public FairNonFairLockThread(FairNonFairLockService service, String name) {
        super(name);
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

public class FairNonFairLockTest {

    public static void main(String[] args) throws InterruptedException {
        FairNonFairLockService service = new FairNonFairLockService(false);
        List<FairNonFairLockThread> list = new ArrayList<>();
        List<FairNonFairLockThread> list2 = new ArrayList<>();
        for(int i = 0;i<10;i++){
            FairNonFairLockThread thread = new FairNonFairLockThread(service,"array1-"+i);
            list.add(thread);
        }
        for(FairNonFairLockThread thread : list){
            thread.start();
        }
        for(int j = 0;j<10;j++){
            FairNonFairLockThread thread = new FairNonFairLockThread(service,"array2-"+j);
            list2.add(thread);
        }
        Thread.sleep(500);
        for(FairNonFairLockThread thread : list2){
            thread.start();
        }
    }
}
