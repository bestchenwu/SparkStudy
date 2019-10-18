package com.hadoopStudy.mapReduce;

import com.spark.constants.SymbolConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
//import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * 解析形如2019-10-18_33这样的字符串,前面是年份,后面是温度<br/>
 * org.apache.hadoop.mapred.Mapper 这个是旧的Hadoop api<br/>
 * org.apache.hadoop.mapreduce.Mapper这个是新的hadoop api<br/>
 * 由于这里使用mrunit,测试的是mapred.Mapper<br/>
 * 更多新旧API的区别可以参考:https://www.aboutyun.com/thread-6831-1-1.html
 *
 * @author chenwu on 2019.10.18
 */
public class YearTemeratureMapper implements Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        String text = value.toString();
        if (StringUtils.isBlank(text)) {
            return;
        }
        String[] array = text.split(SymbolConstants.SYMBOL_XHX);
        if (array.length < 2) {
            return;
        }
        Text writeKey = new Text(array[0]);
        IntWritable writeValue = new IntWritable(Integer.parseInt(array[1]));
        output.collect(writeKey,writeValue);
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf job) {

    }

    //    @Override
//    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        String text = value.toString();
//        if (StringUtils.isBlank(text)) {
//            return;
//        }
//        String[] array = text.split(SymbolConstants.SYMBOL_XHX);
//        if (array.length < 2) {
//            return;
//        }
//        Text writeKey = new Text(array[0]);
//        IntWritable writeValue = new IntWritable(Integer.parseInt(array[1]));
//        context.write(writeKey, writeValue);
//    }
}
