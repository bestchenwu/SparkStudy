package com.hadoopStudy.hadoopCommon;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlCat {

    static{
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args){
        InputStream is = null;
        try{
            //输入参数hdfs://10.40.11.13:8020/user/chenwu/hadoop/spark-monitor-running.log可以查看到
            is = new URL(args[0]).openStream();
            IOUtils.copyLarge(is,System.out,new byte[2048]);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
