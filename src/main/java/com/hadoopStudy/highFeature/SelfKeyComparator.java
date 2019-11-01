package com.hadoopStudy.highFeature;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparator;

public class SelfKeyComparator extends WritableComparator {

    public SelfKeyComparator(){
        super(IntPair.class);
    }

    @Override
    public int compare(Object a, Object b) {
        IntPair a1 = (IntPair)a;
        IntPair b1 = (IntPair)b;
        //对年份排序取升序
        int yearCompareResult = a1.getFirst().compareTo(b1.getFirst());
        if(yearCompareResult!=0){
            return yearCompareResult;
        }else{
            //对天气排序取降序
            return Math.negateExact(a1.getSecond().compareTo(b1.getSecond()));
        }
    }
}
