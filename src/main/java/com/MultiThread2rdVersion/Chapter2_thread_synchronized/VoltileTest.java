package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

class PrintString implements Runnable{

    private boolean isContinuePrint = true;

    public void setContinuePrint(boolean isContinuePrint){
        this.isContinuePrint = isContinuePrint;
    }

    public void printString(){
        while(true){
            System.out.println("print string");
            if(isContinuePrint == false){
                //收到终止信息
                System.out.println("isContinuePrint is false");
                break;
            }
            try{
                Thread.sleep(1*1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run() {
        printString();
    }
}

public class VoltileTest {

    public static void main(String[] args) {
        PrintString printString = new PrintString();
        Thread thread = new Thread(printString);
        thread.start();
        //printString.printString();
        printString.setContinuePrint(false);
    }
}
