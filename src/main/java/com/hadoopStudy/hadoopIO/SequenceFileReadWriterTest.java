package com.hadoopStudy.hadoopIO;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.net.URI;

/**
 * https://blog.csdn.net/bitcarmanlee/article/details/78111289<br/>
 * sequenceFile的读写测试<br/>
 * 在hadoop中sequenceFile用于存储二进制的数据,每一个key-value表示一条record<br/>
 * 而纯文本并不适合
 *
 * @author chenwu on 2019.10.17
 */
public class SequenceFileReadWriterTest {

    public static String[] strs = new String[]{"a","b","c","d","e"};
    public static String OUTPUT_PATH = "hdfs://127.0.0.1:9000/user/chenwu/hadoop/sequence.txt";
    public static Configuration conf = new Configuration();

    public static void write(){
        try{
            Path path = new Path(OUTPUT_PATH);
            SequenceFile.Writer writer = SequenceFile.createWriter(conf, SequenceFile.Writer.keyClass(IntWritable.class), SequenceFile.Writer.valueClass(Text.class), SequenceFile.Writer.file(path));
            IntWritable it = new IntWritable();
            Text text = new Text();
            for(int i=0;i<strs.length;i++){
                it.set(i);
                text.set(strs[i]);
                System.out.println(String.format("[%d]\t%s\t%s",writer.getLength(),it,text));
                writer.append(it,text);
            }
            IOUtils.closeStream(writer);
            //SequenceFile.createWriter(fs,conf,path, IntWritable.class, Text.class);
        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }

    public static void read(){
        try{
            Path path = new Path(OUTPUT_PATH);
            SequenceFile.Reader reader = new SequenceFile.Reader(conf, SequenceFile.Reader.file(path));
            Writable key = (Writable)ReflectionUtils.newInstance(reader.getKeyClass(),conf);
            Writable value = (Writable)ReflectionUtils.newInstance(reader.getValueClass(),conf);
            while(reader.next(key,value)){
                System.out.println(String.format("%s\t%s",key,value));
            }
            IOUtils.closeStream(reader);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        write();
        read();
    }
}
