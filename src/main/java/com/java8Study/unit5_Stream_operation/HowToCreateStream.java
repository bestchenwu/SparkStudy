package com.java8Study.unit5_Stream_operation;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 本程序展示如何创建一个流
 *
 * @author chenwu on 2019.10.11
 */
public class HowToCreateStream {
    public static void main(String[] args) {
        //由值创建流
        //Stream<String> stream =  Stream.of("hello","world");
        //由数组创建流
        //IntStream intStream = Arrays.stream(new int[]{1,2,3});
        //由文件创建流  当文件里有中文的时候,会报java.nio.charset.MalformedInputException: Input length = 1错误
//        try (Stream<String> lines = Files.lines(Paths.get("D:/logs/server.log"), Charset.forName("UTF-8"))) {
//            lines.flatMap((String line)->Arrays.stream(line.split("\\s+"))).filter((String line) -> line.contains("org.apache.zookeeper")).forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //由函数来创建流
        //Stream.iterate(0,n->n+2).limit(3).forEach(System.out::println);

//        Stream<Integer> stream  = Stream.generate(new Supplier<Integer>() {
//
//            int n = 1;
//
//            @Override
//            public Integer get() {
//                n = n*2;
//                return n;
//            }
//        });
        //可以简化为:
        int n = 1;
        Stream<Integer> stream1  = Stream.generate(()->n);
        //stream1.limit(3).forEach(System.out::println);
        //斐波纳契
        Stream.iterate(new int[]{0,1},(int[] ints)->new int[]{ints[1],ints[1]+ints[0]}).limit(5).map(t->t[0]).forEach(System.out::println);
    }
}
