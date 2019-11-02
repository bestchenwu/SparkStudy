package com.hadoopStudy.highFeature;

import org.apache.hadoop.io.WritableComparator;

public class SelfGroupComparator extends WritableComparator {

    public SelfGroupComparator(){
        super(IntPair.class,true);
    }

    @Override
    public int compare(Object a, Object b) {
        IntPair a1 = (IntPair)a;
        IntPair b1 = (IntPair)b;
        return IntPair.compare(a1.getFirst(),b1.getFirst());
    }
}
