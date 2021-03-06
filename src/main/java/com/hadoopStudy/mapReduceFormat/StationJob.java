package com.hadoopStudy.mapReduceFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

/**
 * 测试多路输出<br/>
 * 在提交前先执行hadoop fs -copyFromLocal station_temperature.txt /user/chenwu/hadoop/
 *
 * @author chenwu on 2019.10.30
 */
public class StationJob extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);
        FileSystem fs = FileSystem.get(outputPath.toUri(),conf);
        if(fs.exists(outputPath)){
            fs.delete(outputPath,true);
        }
        Job job = Job.getInstance(conf,"StationJob");
        FileInputFormat.setInputPaths(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);
        job.setMapOutputKeyClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setMapperClass(StationMapper.class);
        job.setReducerClass(StationReducer.class);
        job.setJarByClass(getClass());
        return job.waitForCompletion(true)?1:0;
    }

    public static void main(String[] args) throws Exception {
        StationJob job = new StationJob();
        int resultCode = job.run(args);
        System.exit(resultCode);
    }
}
