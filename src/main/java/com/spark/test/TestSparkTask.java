/*
 * Copyright 1999-2004 yihaodian.com All right reserved. This software is the
 * confidential and proprietary information of yihaodian.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with yihaodian.com.
 */
package com.spark.test;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

/**
 * spark的测试任务
 * 
 * @author sweet 2018-3-4 下午9:56:05
 */
public class TestSparkTask {
    
    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("testSpark");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rdd = sc.textFile("/usr/spark/youwei-store-service.log");
        final Accumulator<Integer> sparkLines = sc.accumulator(0);
        JavaRDD<String> flatMapRDD = rdd.flatMap(new FlatMapFunction<String,String>(){

            @Override
            public Iterator<String> call(String str) throws Exception {
                if(str.contains("youwei")){
                    sparkLines.add(1);
                }
                
                return Arrays.asList(str.split(" ")).iterator();
            }
            
        });
        flatMapRDD.saveAsTextFile("/usr/spark/result.log");
        System.out.println("spark lines:"+sparkLines.value());
        sc.close();
    }
}
