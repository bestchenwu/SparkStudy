package com.hadoopStudy.highFeature;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;

/**
 * 实现自分区<br/>
 * 输入是year_temperature.txt
 *
 * @author chenwu on 2019.11.1
 */
public class SelfParitionJob extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Path input = new Path(args[0]);
        Path output = new Path(args[1]);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(output.toUri(),conf);
        if(fs.exists(output)){
            fs.delete(output,true);
        }
        Job job = Job.getInstance(conf,"SelfParitionJob");
        job.setMapperClass(SelfParitionMapper.class);
        job.setReducerClass(SelfPartitionReducer.class);
        job.setOutputKeyClass(IntPair.class);
        job.setOutputValueClass(NullWritable.class);
        job.setSortComparatorClass(SelfKeyComparator.class);
        job.setGroupingComparatorClass(SelfGroupComparator.class);
        job.setPartitionerClass(SelfPartitioner.class);
        return job.waitForCompletion(true)?1:0;
    }

    public static void main(String[] args)  throws Exception  {
        SelfParitionJob selfParitionJob = new SelfParitionJob();


        int code = selfParitionJob.run(args);
        System.exit(code);
    }
}
