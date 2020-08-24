package com.MultiThread2rdVersion.Chapter7_thread_extra;

class StateThread extends Thread{

    public StateThread(){
        System.out.println("state1:"+this.getState());
    }

    @Override
    public void run() {
        System.out.println("state2:"+this.getState());
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}

public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException{
        StateThread stateThread = new StateThread();
        stateThread.start();
        Thread.sleep(500);
        System.out.println("state3:"+stateThread.getState());
    }


}
