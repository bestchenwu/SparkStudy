package com.hadoopStudy.highFeature;

import com.spark.constants.SymbolConstants;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 输入是1990_35<br/>
 *        1998_33
 *   这样的形式<br/>
 *   最终输出到文件里是IntPair
 *
 * @author chenwu on 2019.11.1
 */
public class SelfParitionMapper extends Mapper<LongWritable, Text, IntPair,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String year_temperature = value.toString();
        int index = year_temperature.indexOf(SymbolConstants.SYMBOL_XHX);
        int year = Integer.parseInt(year_temperature.substring(0,index));
        int temperature = Integer.parseInt(year_temperature.substring(index+1));
        context.write(new IntPair(new IntWritable(year),new IntWritable(temperature)),value);
    }
}
