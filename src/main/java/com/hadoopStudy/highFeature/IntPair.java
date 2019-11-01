package com.hadoopStudy.highFeature;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 包含两个IntWritable的输出类
 *
 * @author chenwu on 2019.11.1
 */
public class IntPair implements WritableComparable<IntPair> {

    private IntWritable first;
    private IntWritable second;

    public IntPair(){
        first = new IntWritable();
        second = new IntWritable();
    }

    public IntPair(IntWritable first,IntWritable second){
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        return 31*first.hashCode()+second.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IntPair)){
            return false;
        }
        IntPair other = (IntPair)obj;
        return this.first.equals(other.first)&&this.second.equals(other.second);
    }

    @Override
    public int compareTo(IntPair o) {
        IntPair other = (IntPair)o;
        int firstResult = first.compareTo(other.first);
        return firstResult==0?second.compareTo(other.second):firstResult;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        first.write(out);
        second.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        first.readFields(in);
        second.readFields(in);
    }

    public IntWritable getFirst() {
        return first;
    }

    public IntWritable getSecond() {
        return second;
    }
}
