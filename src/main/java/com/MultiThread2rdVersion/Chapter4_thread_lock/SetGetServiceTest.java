package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用Condition实现交替输出
 *
 * @author chenwu on 2020.8.17
 */

class GetSetService {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void testGet() {
        while(true){
            try {
                lock.lock();
                if (hasValue == false) {
                    condition.await();
                }
                System.out.println("get value");
                hasValue = false;
                condition.signal();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void testSet() {
        while(true){
            try {
                lock.lock();
                if (hasValue == true) {
                    condition.await();
                }
                System.out.println("set value");
                hasValue = true;
                condition.signal();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}

public class SetGetServiceTest {

    public static void main(String[] args) {
        final GetSetService getSetService = new GetSetService();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                getSetService.testGet();
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                getSetService.testSet();
            }
        };
        thread1.start();
        thread2.start();
    }
}
