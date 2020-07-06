package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

class HasSelfPrivateNum {

    private int i = 0;

    public synchronized void setI(String userName){
        if (userName.equals("a")) {
            System.out.println("a set 100 ok");
            i = 100;
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        } else {
            System.out.println("b set 200 ok");
            i = 200;
        }
        System.out.println("username:" + userName + ",i=" + i);
    }
}

class HasSelfPrivateNumThread extends Thread {

    private String userName;
    private HasSelfPrivateNum self;

    public HasSelfPrivateNumThread(String userName, HasSelfPrivateNum self) {
        this.userName = userName;
        this.self = self;
    }

    @Override
    public void run() {
        self.setI(userName);
    }
}

public class HasSelfPrivateNumTest {

    public static void main(String[] args) {
        HasSelfPrivateNum self = new HasSelfPrivateNum();
        HasSelfPrivateNumThread thread1 = new HasSelfPrivateNumThread("a", self);
        HasSelfPrivateNumThread thread2 = new HasSelfPrivateNumThread("b", self);
        thread1.start();
        thread2.start();
        /**
         * a set 100 ok
         * b set 200 ok
         * username:b,i=200
         * username:a,i=200 从这可以看出a线程的i值被b线程改变了
         */
    }
}
