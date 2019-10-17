package com.hadoopStudy.hadoopIO;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
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
        FileInputStream fis = new FileInputStream("/data/problem/brand.txt");
        File file = new File("/data/problem/brand_compress.txt");
        FileOutputStream fos = new FileOutputStream(file,true);
        //这是使用自己创建一个压缩器的方式
        //CompressionOutputStream outputStream = compressionCodec.createOutputStream(fos);
        //IOUtils.copyBytes(fis,outputStream,2046,true);
        //以下是通过压缩池来创建一个压缩器
        Compressor compressor = null;
        try{
            compressor = CodecPool.getCompressor(compressionCodec);
            CompressionOutputStream outputStream1 = compressionCodec.createOutputStream(fos, compressor);
            IOUtils.copyBytes(fis,outputStream1,2046,false);
            outputStream1.finish();
        }finally {
            CodecPool.returnCompressor(compressor);
        }
        //CompressionInputStream compressInputStream = compressionCodec.createInputStream(new FileInputStream("D:\\logs\\test_driver_log_compress.txt"));
        //IOUtils.copyBytes(compressInputStream,System.out,2046,true);
    }
}
