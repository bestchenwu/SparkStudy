package com.hadoopStudy.highFeature;

import org.apache.hadoop.io.WritableComparator;

public class SelfGroupComparator extends WritableComparator {

    public SelfGroupComparator(){
        super(IntPair.class);
    }

    @Override
    public int compare(Object a, Object b) {
        IntPair a1 = (IntPair)a;
        IntPair b1 = (IntPair)b;
        return a1.getFirst().compareTo(b1.getFirst());
    }
}
