package com.hadoopStudy.mapReduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * 从一组气温中找一个最大值
 *
 * @author chenwu on 2019.10.18
 */
public class YearTemeratureReducer implements Reducer<Text, IntWritable, Text, IntWritable> {

    private int max_value;

    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        values.forEachRemaining((IntWritable intWritable) -> {
            max_value = Math.max(max_value, intWritable.get());
        });
        output.collect(key, new IntWritable(max_value));
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf job) {
        max_value = Integer.MIN_VALUE;
    }
}
