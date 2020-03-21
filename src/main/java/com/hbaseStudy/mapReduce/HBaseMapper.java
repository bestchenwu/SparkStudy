package com.hbaseStudy.mapReduce;

import com.spark.constants.SymbolConstants;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * {@link HBaseMapReduceJob}的mapper类
 *
 * @author chenwu on 2020.3.21
 */
public class HBaseMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String str = value.toString();
        String[] splitArray = str.split(SymbolConstants.SYMBOL_XHX);
        if(splitArray.length<2){
            return;
        }
        //第一项为row,第二项为value
        ImmutableBytesWritable row = new ImmutableBytesWritable(Bytes.toBytes(splitArray[0]));
        Put put = new Put(Bytes.toBytes(splitArray[0]));
        put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("hbaseColumn"),Bytes.toBytes(splitArray[1]));
        context.write(row,put);
    }
}
