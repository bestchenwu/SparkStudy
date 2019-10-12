package com.java8Study.unit7_parallel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * https://www.cnblogs.com/senlinyang/p/7885964.html  fork/join框架<br/>
 * 也可以看https://segmentfault.com/a/1190000019549838?utm_source=tag-newest<br/>
 *
 * 求和任务(将多个数的相加细分到两两相加)
 * @author  chenwu on 2019.10.12
 */
public class CountTask extends RecursiveTask<Integer> {

    private int start;
    private int end;
    private static final int CAN_COMPUTE_THRESHOLD = 2;

    public CountTask(int start,int end){
        this.start = start ;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if(end-start<=CAN_COMPUTE_THRESHOLD){
            for(int i = start;i<=end;i++){
                sum+=i;
            }

        }else{
            int middle = (end-start)/2;
            CountTask task1 = new CountTask(start,middle);
            CountTask task2 = new CountTask(middle+1,end);
            //计算子任务
            task1.fork();
            task2.fork();
            //获取子任务结果
            Integer result1 = task1.join();
            Integer result2 = task2.join();
            sum = result1+result2;
        }
        return sum;
    }

    public static void main(String[] args){
        CountTask countTask = new CountTask(1,4);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> future = forkJoinPool.submit(countTask);
        try{
            Integer result = future.get();
            System.out.println(result);
        }catch(InterruptedException |ExecutionException e){
            e.printStackTrace();
        }

    }
}
