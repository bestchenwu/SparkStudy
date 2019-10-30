package com.hadoopStudy.mapReduceFormat;

import com.spark.constants.SymbolConstants;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 从天气里提取站点<br/>
 * 天气内容类似于paris_2019-10-20_33.5
 *
 * @author chenwu on 2019.10.30
 */
public class StationMapper extends Mapper<LongWritable, Text,Text,Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //温度
        String temperature = value.toString();
        String station = temperature.substring(0,temperature.indexOf(SymbolConstants.SYMBOL_XHX));
        String year_temperature = temperature.substring(temperature.indexOf(SymbolConstants.SYMBOL_XHX)+1);
        context.write(new Text(station),new Text(year_temperature));
    }
}
