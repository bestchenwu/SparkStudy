package com.hadoopStudy.mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

/**
 * 综合运用{@link YearTemeratureMapper}和{@link YearTemeratureReducer}
 *
 * @author chenwu on 2019.10.18
 */
public class YearTemeratureJob extends Configured implements Tool {



    @Override
    public int run(String[] args) throws Exception {
        if(args.length<2){
            throw new IllegalStateException("args length must be greater than 2");
        }
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,"YearTemeratureJob");
        job.setJarByClass(getClass());
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setMapperClass(YearTemeratureV2Mapper.class);
        job.setReducerClass(YearTemeratureV2Reducer.class);
        job.setCombinerClass(YearTemeratureV2Reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        return job.waitForCompletion(true)?0:1;
    }

    public static void main(String[] args) throws Exception {
        YearTemeratureJob job = new YearTemeratureJob();
        int exitCode = job.run(args);
        System.exit(exitCode);
    }
}
