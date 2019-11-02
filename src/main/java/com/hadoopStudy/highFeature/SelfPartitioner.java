package com.hadoopStudy.highFeature;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class SelfPartitioner extends Partitioner<IntPair, NullWritable> {

    @Override
    public int getPartition(IntPair intPair, NullWritable nullWritable, int numPartitions) {
        int year = intPair.getFirst();
        System.err.println("====================getPartition:"+year);
        if(year>1990){
            return 2;
        }else{
            return 1;
        }
    }
}
