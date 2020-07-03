package com.MultiThread2rdVersion.Chapter1_thread_api;

class MyInterupteThread extends Thread {

    @Override
    public void run() {
//        for(int i = 0;i<=500*1000;i++){
//            if(this.isInterrupted()){
//                System.out.println("MyInterupteThread stop");
//                break;
//            }
//            System.out.println("i="+i);
//        }
//        System.out.println("MyInterupteThread stop,but program is continuing");
        try {
//            System.out.println("sleep begin");
//            Thread.sleep(200*100);
//            System.out.println("sleep end");
            for (int i = 0; i <= 500 * 1000; i++) {
               System.out.println("i="+i);
            }
            Thread.sleep(200);
        } catch (InterruptedException e) {
            //线程在sleep状态下，被中断，这时候会清除状态值 isInterrupt返回false
            System.out.println("when sleep,then get interrupt:" + this.isInterrupted());
            e.printStackTrace();
        }
    }
}

public class InteruptThreadTest {

    public static void main(String[] args) throws InterruptedException {
        MyInterupteThread myThread = new MyInterupteThread();
        myThread.start();
        Thread.sleep(1 * 100);
        myThread.interrupt();
//        System.out.println("myThread is Interrupted1?"+myThread.interrupted());
//        System.out.println("myThread is Interrupted2?"+myThread.interrupted());
        //Thread.currentThread().interrupt();
        //interrupted是一个静态方法，测试当前线程是否中断,第一次调用完成后会清除线程的中断状态,所以第二次调用会返回false
        //isInterrupted测试这个线程是否中断
//        System.out.println("currentThread is Interrupted1?"+Thread.interrupted());
//        System.out.println("currentThread is Interrupted2?"+Thread.interrupted());
        //System.out.println("zzzzzzz");
    }
}
