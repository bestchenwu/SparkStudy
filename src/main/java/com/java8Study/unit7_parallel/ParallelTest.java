package com.java8Study.unit7_parallel;

import com.spark.common.util.CommonDateUtil;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * 并行计算测试
 *
 * @author chenwu on 2019.10.12
 */
public class ParallelTest {

    public static void main(String[] args) {
//        long t1 =  CommonDateUtil.getCurrentTimeStamp();
//        int nonParall = IntStream.range(1,100000).boxed().reduce(0,(a,b)->a+b);
//        System.out.println(nonParall);
//        long t2 = CommonDateUtil.getCurrentTimeStamp();
//        //输出139毫秒
//        System.out.println("nonParall needs:"+ (t2-t1));
//        t1 =  CommonDateUtil.getCurrentTimeStamp();
//        int parall = IntStream.range(1,100000).boxed().parallel().reduce(0,(a,b)->a+b);
//        System.out.println(parall);
//        t2 = CommonDateUtil.getCurrentTimeStamp();
//        //输出22毫秒
//        System.out.println("parall needs:"+ (t2-t1));

        //错误的使用
        //使用了带状态的累加器,本应该输出5050，却输出了5021、4920等不正确的数字
        Accumulator accumulator = new Accumulator();
        IntStream.rangeClosed(1, 100).boxed().parallel().forEach(accumulator::add);
        System.out.println("total:" + accumulator.total);
    }


}

class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }
}
