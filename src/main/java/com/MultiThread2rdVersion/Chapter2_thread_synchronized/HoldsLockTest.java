package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

public class HoldsLockTest {

    public static void main(String[] args) {
        System.out.println("A "+Thread.holdsLock(HoldsLockTest.class));
        synchronized (HoldsLockTest.class){
            //holdsLock用于判断当前线程是否拥有某个对象锁
            System.out.println("B "+Thread.holdsLock(HoldsLockTest.class));
        }
        System.out.println("C "+Thread.holdsLock(HoldsLockTest.class));
    }
}
