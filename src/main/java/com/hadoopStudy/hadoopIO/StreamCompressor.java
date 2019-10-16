package com.hadoopStudy.hadoopIO;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 测试hadoop压缩
 */
public class StreamCompressor {

    public static void main(String[] args) throws Exception{
        String compressCodeClassName = args[0];
        Class<?> clazz = Class.forName(compressCodeClassName);
        Configuration conf = new Configuration();
        CompressionCodec compressionCodec = (CompressionCodec)ReflectionUtils.newInstance(clazz, conf);
        FileInputStream fis = new FileInputStream("D:\\logs\\test_driver_log.txt");
        File file = new File("D:\\logs\\test_driver_log_compress.txt");
        FileOutputStream fos = new FileOutputStream(file,false);
        CompressionOutputStream outputStream = compressionCodec.createOutputStream(fos);
        IOUtils.copyBytes(fis,outputStream,2046,true);
        CompressionInputStream compressInputStream = compressionCodec.createInputStream(new FileInputStream("D:\\logs\\test_driver_log_compress.txt"));
        IOUtils.copyBytes(compressInputStream,System.out,2046,true);
    }
}
