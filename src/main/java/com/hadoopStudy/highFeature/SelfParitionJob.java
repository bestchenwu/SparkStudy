package com.hadoopStudy.highFeature;
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
        FileInputFormat.setInputPaths(job,input);
        FileOutputFormat.setOutputPath(job,output);
        job.setJarByClass(getClass());
        job.setMapperClass(SelfParitionMapper.class);
        job.setReducerClass(SelfPartitionReducer.class);
        job.setMapOutputKeyClass(IntPair.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        //reduce拿到map的输出结果后,会使用自排序
        //todo:说明没有使用到我自定义的comarator
        //todo:和只有一个reducer有关系,输入只有一个mapper,因为文件没有分片？
        job.setSortComparatorClass(SelfKeyComparator.class);
        //然后构造一个key对应的value迭代器,这个时候就需要使用到group
        //job.setGroupingComparatorClass(SelfGroupComparator.class);
        //在map的最终阶段会调用分组函数 只要这个比较器比较的两个key相同，他们就属于同一个组，它们的value放在一个value迭代器
        //job.setPartitionerClass(SelfPartitioner.class);
        return job.waitForCompletion(true)?1:0;
    }

    public static void main(String[] args)  throws Exception  {
        SelfParitionJob selfParitionJob = new SelfParitionJob();


        int code = selfParitionJob.run(args);
        System.exit(code);
    }
}
