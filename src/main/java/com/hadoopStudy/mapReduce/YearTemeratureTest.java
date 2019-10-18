package com.hadoopStudy.mapReduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapDriver;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * 利用org.apache.mrunit测试map/reduce
 *
 * @author chenwu on 2019.10.18
 */
public class YearTemeratureTest {

    //@Test
    public void testMapper() throws IOException {
        Text text = new Text("2019-10-18_33");
        MapDriver<LongWritable, Text, Text, IntWritable> mapDriver = new MapDriver<>();
        mapDriver.withMapper(new YearTemeratureMapper())
                .withInput(new LongWritable(0l), text)
                .withOutput(new Text("2019-10-18"), new IntWritable(33))
                .runTest();
    }

    @Test
    public void testReducer() throws IOException {
        ReduceDriver<Text, IntWritable, Text, IntWritable> reducerDriver = new ReduceDriver<>();
        reducerDriver.withReducer(new YearTemeratureReducer()).
                withInput(new Text("2019-10-18"), Arrays.asList(new IntWritable(8), new IntWritable(-5), new IntWritable(18)))
                .withOutput(new Text("2019-10-18"), new IntWritable(18)).runTest();

    }
}
