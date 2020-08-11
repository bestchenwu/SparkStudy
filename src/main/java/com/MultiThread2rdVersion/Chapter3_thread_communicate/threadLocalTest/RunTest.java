package com.MultiThread2rdVersion.Chapter3_thread_communicate.threadLocalTest;

/**
 * ThreadLocal的测试
 *
 * @author chenwu on 2020.8.11
 */
public class RunTest {

    public static ThreadLocal<String> t1 = new ThreadLocal<String>();

    public static void main(String[] args) {
        if (t1.get() == null) {
            System.out.println("null value");
            t1.set("test");
        }
        System.out.println(t1.get());
    }
}
