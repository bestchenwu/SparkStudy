package com.MultiThread2rdVersion.Chapter6_thread_singleton;

class MyObject {

    //饿汉模式,类在加载的时候即创建对象
    //private static MyObject myObject = new MyObject();
    private static volatile MyObject myObject;

    private MyObject() {

    }

    private static String username;
    private static String password;

    //饿汉模式
//    public static MyObject getInstance() {
//        return myObject;
//    }
    //懒汉模式
    public static MyObject getInstance() {
        try {
            if (myObject == null) {
                Thread.sleep(3000);
                synchronized (MyObject.class) {
                    //double-check locking
                    if (myObject == null) {
                        myObject = new MyObject();
                    }
                }
            }
            return myObject;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MyObject1 {

    /**
     * 使用内部类的方式来实现多线程共享实例的方式
     */
    private static class MyObjectHandler {
        public static MyObject1 myObject = new MyObject1();
    }

    private MyObject1() {

    }

    public static MyObject1 getInstance() {
        return MyObjectHandler.myObject;
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + MyObject.getInstance().hashCode());
    }
}

class MyThread1 extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + MyObject1.getInstance().hashCode());
    }
}

public class SingleTonTest {

    public static void main(String[] args) {
//        MyThread thread1 = new MyThread();
//        thread1.setName("thread1");
//        MyThread thread2 = new MyThread();
//        thread2.setName("thread2");
//        MyThread thread3 = new MyThread();
//        thread3.setName("thread3");
//        thread1.start();
//        thread2.start();
//        thread3.start();

        MyThread1 thread1 = new MyThread1();
        thread1.setName("thread1");
        MyThread1 thread2 = new MyThread1();
        thread2.setName("thread2");
        MyThread1 thread3 = new MyThread1();
        thread3.setName("thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
