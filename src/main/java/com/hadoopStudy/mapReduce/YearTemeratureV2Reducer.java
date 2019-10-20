package com.hadoopStudy.mapReduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 使用mapreducer.Reducer新式API
 *
 * @author chenwu on 2019.10.18
 */
public class YearTemeratureV2Reducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    private int max_value;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
        this.max_value = Integer.MIN_VALUE;
    }

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        values.forEach((IntWritable intWritable) -> {
            max_value = Math.max(max_value, intWritable.get());
        });
        context.write(key,new IntWritable(max_value));
    }
}
