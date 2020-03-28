package com.hbaseStudy.mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.JobUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;


/**
 * hbase表copy的map/reduce job<br/>
 * 将表test的列簇下的cf拷贝到testCopy下的cf,这里只拷贝columnName比b小于等于
 *
 * @author chenwu on 2020.3.28
 */
public class HBaseTableCopyJob extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        Job job = Job.getInstance(conf,"HBaseTableCopyJob");
        job.setJarByClass(getClass());
        Scan scan = new Scan();
        scan.setCaching(5);
        scan.setCacheBlocks(true);
        TableMapReduceUtil.initTableMapperJob("test",scan,HBaseTableCopyMapper.class, ImmutableBytesWritable.class, Put.class,job);
        TableMapReduceUtil.initTableReducerJob("testCopy",HBaseTableCopyReducer.class,job);
        boolean result = job.waitForCompletion(true);
        return result?1:0;
    }

    public static void main(String[] args) throws Exception {
        HBaseTableCopyJob job = new HBaseTableCopyJob();
        int result = job.run(args);
        System.out.println("result:"+result);
    }
}
