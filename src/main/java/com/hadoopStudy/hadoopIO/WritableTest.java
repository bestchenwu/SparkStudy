package com.hadoopStudy.hadoopIO;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.StringUtils;
import org.junit.Assert;

import java.io.*;

/**
 * hadoop的io序列化接口
 *
 * @author chenwu on 2019.10.17
 */
public class WritableTest {

    public static void main(String[] args){
        IntWritable intWritable = new IntWritable();
        intWritable.set(163);
        byte[] bytes = serialize(intWritable);
        //因为一个int 占4个字节
        Assert.assertEquals(4,bytes.length);
        //16进制的字符串,每个字符占4个二进制位,所以一个byte可以用两个十六进制字符来表达
        Assert.assertEquals(StringUtils.byteToHexString(bytes),"000000a3");
        //对bytes进行反序列化
        IntWritable writable = deserialize(bytes);
        Assert.assertEquals(163,writable.get());
    }

    public static byte[] serialize(Writable writable){
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();DataOutputStream dot = new DataOutputStream(bos);){
            writable.write(dot);
            return bos.toByteArray();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static IntWritable deserialize(byte[] bytes){
        try(ByteArrayInputStream bis = new ByteArrayInputStream(bytes);DataInputStream dis = new DataInputStream(bis)){
            IntWritable writable = new IntWritable();
            writable.readFields(dis);
            return writable;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


}
