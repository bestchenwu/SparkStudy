package com.java8Study.unit2;

import java.io.*;
import java.util.concurrent.Callable;

/**
 * 表示该接口会被设计为一个函数式接口
 */
@FunctionalInterface
interface BuffedReaderProcessor{
    String process(BufferedReader br) throws IOException;
}

public class FileTest {


    /**
     * JDK1.7及以下的写法
     *
     * @return
     * @throws IOException
     */
    public static String processFile() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(""))){
            return br.readLine();
        }
    }

    public static String processFile1(String fileName,BuffedReaderProcessor bp) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            return bp.process(br);
        }
    }

    public static void main(String[] args) throws  IOException{
        String result = processFile1("",(BufferedReader br)->br.readLine()+br.readLine());
        //该语法会报lambda target type must be a FunctionalInterface
        //Object o = ()->System.out.println(33);
        //改成Runnable即可,因为Runnable实现了FunctionalInterface接口
        Runnable runnable = ()->System.out.println("");
        //Callable<Integer> callable = ()->42;
        System.out.println(result);
    }
}
