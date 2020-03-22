package com.hbaseStudy.mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;

/**
 * hbase mapreduce例子<br/>
 * 输入地址:hdfs://127.0.0.1:9000/user/chenwu/hadoop/year_temperatrue.txt
 *
 * 提交命令hadoop jar xx.jar /user/chenwu/hadoop/year_temperatrue.txt
 *
 * @author chenwu on 2020.3.21
 */
public class HBaseMapReduceJob extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Path inputPath = new Path(args[0]);
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "HBaseMapReduceJob");
        job.setJarByClass(getClass());
        FileInputFormat.setInputPaths(job, inputPath);
        job.setMapperClass(HBaseMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);
        TableMapReduceUtil.initTableReducerJob("test", null, job);
        //todo:我把这两行代码来自于util里面的代码改变后，依然可以写入到hbase
        // todo:说明这里的outputKeyClass ,outputValueClass 在设置了OutputFormat之后，没有任何用处
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(NullWritable.class);
        return job.waitForCompletion(true) ? 1 : 0;
    }

    public static void main(String[] args) throws Exception {
        HBaseMapReduceJob hBaseMapReduceJob = new HBaseMapReduceJob();
        int result = hBaseMapReduceJob.run(args);
        System.out.println("result="+result);
    }
}
