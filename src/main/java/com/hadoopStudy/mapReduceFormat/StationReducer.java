package com.hadoopStudy.mapReduceFormat;

import com.spark.constants.SymbolConstants;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;

/**
 * 多路输出
 *
 * @author chenwu on 2019.10.30
 */
public class StationReducer extends Reducer<Text, Text, NullWritable, Text> {

    private MultipleOutputs<NullWritable, Text> multipleOutputs;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        multipleOutputs = new MultipleOutputs<NullWritable, Text>(context);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        multipleOutputs.close();
    }

    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for(Text value:values){
            String year_temperature = value.toString();
            String year = year_temperature.substring(0,year_temperature.indexOf(SymbolConstants.SYMBOL_XHX));
            String temperature = year_temperature.substring(year_temperature.indexOf(SymbolConstants.SYMBOL_XHX)+1);
            String station = key.toString();
            String basePath = String.format("/user/chenwu/hadoop1/%s/%s/part",station,year);
            multipleOutputs.write(NullWritable.get(),new Text(temperature),basePath);
        }
    }
}
