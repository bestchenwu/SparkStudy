package com.MultiThread2rdVersion.Chapter7_thread_extra;

/**
 * 测试{@link Thread#enumerate(Thread[])}
 */
public class ThreadEnumerateTest {

    public static void main(String[] args) {
        int activeCount = Thread.currentThread().activeCount();
        Thread[] threadArray = new Thread[activeCount];
        Thread.enumerate(threadArray);
        for (Thread thread : threadArray) {
            System.out.println("name:" + thread.getName());
        }
    }
}
