package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

class MyObject {

    public synchronized  void methodA() {
        System.out.println("begin "+Thread.currentThread().getName() + " encounter");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}

class MyObjectThread extends Thread {

    private MyObject myObject;

    public MyObjectThread(MyObject myObject) {
        this.myObject = myObject;
    }


    @Override
    public void run() {
        myObject.methodA();
    }
}

public class MyObjectTest {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        MyObjectThread thread1 = new MyObjectThread(myObject);
        MyObjectThread thread2 = new MyObjectThread(myObject);
        thread1.start();
        thread2.start();
    }
}
