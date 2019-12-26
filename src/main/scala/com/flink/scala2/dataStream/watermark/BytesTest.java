package com.flink.scala2.dataStream.watermark;

public class BytesTest {

    public static void main(String[] args) {
         byte[] bytes = new byte[]{65,66,67,68};
         String str = new String(bytes);
        System.out.println(str);
    }
}
