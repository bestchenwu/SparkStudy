package com.MultiThread2rdVersion.Chapter7_thread_extra;

class MyThread extends Thread{

    @Override
    public void run() {
        String username = null;
        System.out.println(username.hashCode());
    }
}

public class ExceptionCaughtHandlerTest {

    public static void main(String[] args) {
        MyThread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程:"+t.getName()+" 异常");
                e.printStackTrace();
            }
        });
        MyThread thread1 = new MyThread();
        thread1.setName("thread1");
//        thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                System.out.println("线程:"+t.getName()+" 异常");
//                e.printStackTrace();
//            }
//        });
        thread1.start();
        MyThread thread2 = new MyThread();
        thread2.setName("thread2");
        thread2.start();
    }
}
