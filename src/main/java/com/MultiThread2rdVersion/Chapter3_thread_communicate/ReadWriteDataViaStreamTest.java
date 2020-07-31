package com.MultiThread2rdVersion.Chapter3_thread_communicate;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 通过jdk提供的Stream来完成数据读写
 *
 * @author chenwu on 2020.7.31
 */

class WriteDataThread extends Thread {

    private PipedOutputStream pos;

    public WriteDataThread(PipedOutputStream pos){
        this.pos = pos;
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 10; i++) {
                String data = i+" num";
                pos.write(data.getBytes());
                System.out.println("write:"+data);
            }
            pos.flush();
            pos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

class ReadDataThread extends Thread{

    private PipedInputStream pis;

    public ReadDataThread(PipedInputStream pis){
        this.pis = pis;
    }

    @Override
    public void run(){
        byte[] byteArray = new byte[20];
        try{
            int readLength = pis.read(byteArray);
            while(readLength!=-1){
                String readData = new String(byteArray,0,readLength);
                System.out.println("read:"+readData);
                readLength = pis.read(byteArray);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

public class ReadWriteDataViaStreamTest {

    public static void main(String[] args) throws IOException,InterruptedException {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        pos.connect(pis);
        WriteDataThread writeDataThread = new WriteDataThread(pos);
        ReadDataThread readDataThread = new ReadDataThread(pis);
        readDataThread.start();
        Thread.sleep(200);
        writeDataThread.start();
    }

}
