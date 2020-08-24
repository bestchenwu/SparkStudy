package com.MultiThread2rdVersion.Chapter6_thread_singleton;

/**
 * 利用枚举类来构造单例类
 *
 * @author chenwu on 2020.8.24
 */
enum ConnectionObject {
    ConnectonFactory;

    private UserInfo userInfo;

    private ConnectionObject() {
        try {
            Thread.sleep(500);
            userInfo = new UserInfo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }
}

class ConnectionThread extends Thread{

    @Override
    public void run() {
        System.out.println("userInfo hashCode:"+ConnectionObject.ConnectonFactory.getUserInfo().hashCode());
    }
}

public class SingletonEnumTest {

    public static void main(String[] args) {
        ConnectionThread[] threadArray = new ConnectionThread[5];
        for(int i = 0;i<5;i++){
            ConnectionThread connectionThread = new ConnectionThread();
            threadArray[i] = connectionThread;
        }
        for(ConnectionThread connectionThread : threadArray){
            connectionThread.start();
        }
    }
}
