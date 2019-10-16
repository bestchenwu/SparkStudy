package com.hadoopStudy.hadoopCommon;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

public class FileSystemCat {

    public static void main(String[] args){
        String url = args[0];
        Configuration conf = new  Configuration();
        try{
            FileSystem fs = FileSystem.get(URI.create(url),conf);
            FSDataInputStream is = fs.open(new Path(url));
            IOUtils.copyBytes(is,System.out,2048,false);
            //回到开头,再重新输出一次
            is.seek(0l);
            System.out.println("once again");
            IOUtils.copyBytes(is,System.out,2048,true);
        }catch(IOException e){
            e.printStackTrace();;
        }
    }
}
