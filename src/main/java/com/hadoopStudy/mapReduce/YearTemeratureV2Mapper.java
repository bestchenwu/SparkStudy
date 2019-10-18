package com.hadoopStudy.mapReduce;

import com.spark.constants.SymbolConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 使用mapreduce.Mapper 新API
 *
 * @author chenwu on 2019.10.18
 */
public class YearTemeratureV2Mapper extends Mapper<LongWritable,Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
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
        context.write(writeKey,writeValue);
    }
}
