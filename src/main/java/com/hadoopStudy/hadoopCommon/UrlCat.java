package com.hadoopStudy.hadoopCommon;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
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
            IOUtils.copyBytes(is,System.out,2048,true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
