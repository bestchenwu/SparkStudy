package com.MultiThread2rdVersion.Chapter3_thread_communicate;

class GetValueService{

    private Object lock = new Object();

    public void getValue(){
        try{
            synchronized (lock){
                if(ValueObject.value.equals("")){
                    lock.wait();
                }
                System.out.println("value get:"+ValueObject.value);
                ValueObject.value = "";
                Thread.sleep(2*1000);
                lock.notify();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void setValue(){
        try{
            synchronized (lock){
                if(!ValueObject.value.equals("")){
                    lock.wait();
                }
                ValueObject.value = "nowTime-"+System.currentTimeMillis();
                System.out.println("value set:"+ValueObject.value);
                Thread.sleep(2*1000);
                lock.notify();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class GetValueThread extends Thread{

    public GetValueService service;

    public GetValueThread(GetValueService service){
        this.service = service;
    }

    @Override
    public void run() {
        while (true){
            service.getValue();
        }
    }
}

class SetValueThread extends Thread{

    public GetValueService service;

    public SetValueThread(GetValueService service){
        this.service = service;
    }

    @Override
    public void run() {
        while (true){
            service.setValue();
        }
    }
}

public class GetSetValueTest {

    public static void main(String[] args) {
        GetValueService service = new GetValueService();
        GetValueThread thread1 = new GetValueThread(service);
        SetValueThread thread2 = new SetValueThread(service);
        thread1.start();
        thread2.start();
    }
}

class ValueObject{

    public static String value = "";
}
