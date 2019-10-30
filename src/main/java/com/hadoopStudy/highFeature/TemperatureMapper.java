package com.hadoopStudy.highFeature;

import com.spark.constants.SymbolConstants;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 提取出天气内容中的天气值作为输入
 */
public class TemperatureMapper extends Mapper<LongWritable, Text, IntWritable,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String station_year_temperature = value.toString();
        int temperature = Integer.parseInt(station_year_temperature.substring(station_year_temperature.lastIndexOf(SymbolConstants.SYMBOL_XHX)+1));
        context.write(new IntWritable(temperature),value);
    }
}
