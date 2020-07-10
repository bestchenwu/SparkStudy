package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

class StringService{

    public void print(Object object){
        try{
            synchronized (object){
                while(true){
                    System.out.println("thread name;"+Thread.currentThread().getName());
                    Thread.sleep(2*1000);
                }
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class StringThread extends Thread{

    private StringService stringService;

    public StringThread(StringService stringService){
        this.stringService = stringService;
    }

    @Override
    public void run() {
        stringService.print("AA");
    }
}

public class StringSynchronizedTest {

    public static void main(String[] args) {
        StringService stringService = new StringService();
        StringThread threadA = new StringThread(stringService);
        threadA.setName("A");
        StringThread threadB = new StringThread(stringService);
        threadB.setName("B");
        threadA.start();
        threadB.start();
        /**
         * thread name;A
         * thread name;A
         * thread name;A
         */
        //从输出看,由于字符串常量池的特性，所以这里的AA实际上是同一个对象。
        //所以在使用对象做锁的时候，尽量避免使用字符串对象来做锁对象。
    }

}
