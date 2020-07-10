package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

class Service{

    synchronized public void testMethod(){
        if(Thread.currentThread().getName().equals("a")){
            System.out.println("a begin:"+System.currentTimeMillis());
            int i = 1;
            while(i==1){
                //当发生异常的时候，会自动释放synchroinze加在对象上的锁
                if(String.valueOf(Math.random()).substring(0,8).equals("0.123456")){
                    System.out.println("a run exception time:"+System.currentTimeMillis() );
                    Integer.parseInt("a");
                }
            }
        }else{
            System.out.println("b begin:"+System.currentTimeMillis());
        }
    }
}

class ServiceThread extends Thread{

    private Service service;

    public ServiceThread(Service service){
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

public class ServiceTest {

    public static void main(String[] args) {
        Service service = new Service();
        ServiceThread threadA = new ServiceThread(service);
        threadA.setName("a");
        ServiceThread threadB = new ServiceThread(service);
        threadB.setName("b");
        threadA.start();
        try{
            Thread.sleep(5*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        threadB.start();
    }
}
