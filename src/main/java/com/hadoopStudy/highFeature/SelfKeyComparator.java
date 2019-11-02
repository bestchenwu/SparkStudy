package com.hadoopStudy.highFeature;

import org.apache.hadoop.io.WritableComparator;

public class SelfKeyComparator extends WritableComparator {

    public SelfKeyComparator(){
        super(IntPair.class,true);
    }

    @Override
    public int compare(Object a, Object b) {
        IntPair a1 = (IntPair)a;
        IntPair b1 = (IntPair)b;
        throw new IllegalStateException("why don't push a:"+a1+",b="+b1);
//        System.err.println("===============SelfKeyComparator:"+a1);
//        System.err.println("===============SelfKeyComparator:"+b1);
        //对年份排序取升序
//        int yearCompareResult = IntPair.compare(a1.getFirst(),b1.getFirst());
//        if(yearCompareResult!=0){
//            return yearCompareResult;
//        }else{
//            //对气温排序取降序
//            return -IntPair.compare(a1.getSecond(),b1.getSecond());
//        }
    }
}
