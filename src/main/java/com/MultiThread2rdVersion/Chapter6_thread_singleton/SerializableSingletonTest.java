package com.MultiThread2rdVersion.Chapter6_thread_singleton;

import java.io.*;

class UserInfo implements Serializable{

    public String userName ;
    public String password;
}

class MySerializableObject implements Serializable {

    public static MySerializableObject mySerializableObject;

    public static UserInfo userInfo = new UserInfo();

    private MySerializableObject(){

    }

    public static MySerializableObject getInstance(){
        if(mySerializableObject==null){
            mySerializableObject = new MySerializableObject();
        }
        return mySerializableObject;
    }

    protected Object readResolve() throws ObjectStreamException{
        System.out.println("readObject called");
        return MySerializableObject.getInstance();
    }
}

/**
 * 序列化类
 *
 * @author chenwu on 2020.8.21
 */
public class SerializableSingletonTest {

    public static void main(String[] args) {
        MySerializableObject mySerializableObject = MySerializableObject.getInstance();
        System.out.println("before serialzable hashcode:"+mySerializableObject.hashCode()+",userInfo hashCode:"+mySerializableObject.userInfo.hashCode());
        try{
            FileOutputStream fos = new FileOutputStream("D:\\logs\\myObject.txt",false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mySerializableObject);
            oos.flush();
            oos.close();
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            FileInputStream fis = new FileInputStream("D:\\logs\\myObject.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            MySerializableObject object = (MySerializableObject)ois.readObject();
            ois.close();
            fis.close();
            System.out.println("after serialzable hashcode:"+object.hashCode()+",userInfo hashCode:"+object.userInfo.hashCode());
        }catch(ClassNotFoundException | IOException e ){
            e.printStackTrace();
        }
        /**
         * before serialzable hashcode:401424608,userInfo hashCode:1348949648
         * after serialzable hashcode:2083562754,userInfo hashCode:1348949648
         *  说明MySerializableObject重新创建了，但是userInfo得到了复用
         */
    }
}
