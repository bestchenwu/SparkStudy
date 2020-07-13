package com.MultiThread2rdVersion.Chapter3_thread_communicate;

public class Test1 {

    public static void main(String[] args) {
        try{
            String str = "str";
            str.wait();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
