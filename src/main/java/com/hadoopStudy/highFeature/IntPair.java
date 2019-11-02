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

    private int first;
    private int second;

    public IntPair(){
        first = 0;
        second = 0;
    }

    public IntPair(int first,int second){
        this.first = first;
        this.second = second;
    }

    @Override
    public int hashCode() {
        return 31*first+second;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof IntPair)){
            return false;
        }
        IntPair other = (IntPair)obj;
        return this.first==other.first&&this.second==other.second;
    }

    @Override
    public int compareTo(IntPair other) {
      int firstResult = Integer.valueOf(first).compareTo(other.first);
      if(firstResult!=0){
          return firstResult;
      }else{
          return -Integer.valueOf(second).compareTo(other.second);
      }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(first);
        out.writeInt(second);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        first = in.readInt();
        second = in.readInt();
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public static int compare(int first,int second){
        return Integer.valueOf(first).compareTo(Integer.valueOf(second));
    }

    @Override
    public String toString() {
        return String.format("IntPair[first=%d,seconde=%d",first,second);
    }
}
