package com.MultiThread2rdVersion.Chapter6_thread_singleton;

class StaticSingletonObject{

    private static StaticSingletonObject staticSingletonObject;

    static {
        staticSingletonObject = new StaticSingletonObject();
    }

    private StaticSingletonObject(){

    }

    public static StaticSingletonObject getInstance(){
        return staticSingletonObject;
    }
}

class StaticSingletonThread extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" hashCode:"+StaticSingletonObject.getInstance().hashCode());
    }
}

public class StaticSingletonTest {

    public static void main(String[] args) {
        StaticSingletonThread thread1 = new StaticSingletonThread();
        thread1.setName("thread1");
        StaticSingletonThread thread2 = new StaticSingletonThread();
        thread2.setName("thread2");
        StaticSingletonThread thread3 = new StaticSingletonThread();
        thread3.setName("thread3");
        StaticSingletonThread thread4 = new StaticSingletonThread();
        thread4.setName("thread4");
        StaticSingletonThread thread5 = new StaticSingletonThread();
        thread5.setName("thread5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
