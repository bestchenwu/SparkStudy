package com.hadoopStudy.highFeature;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class SelfPartitioner extends Partitioner<IntPair, NullWritable> {

    @Override
    public int getPartition(IntPair intPair, NullWritable nullWritable, int numPartitions) {
        return Math.abs(intPair.getFirst().get()%numPartitions);
    }
}
