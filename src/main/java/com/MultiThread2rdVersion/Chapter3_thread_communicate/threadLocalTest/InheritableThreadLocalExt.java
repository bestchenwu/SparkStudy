package com.MultiThread2rdVersion.Chapter3_thread_communicate.threadLocalTest;

import java.util.Date;

public class InheritableThreadLocalExt extends InheritableThreadLocal {

    @Override
    protected Object childValue(Object parentValue) {
        return parentValue+" in child thread";
    }

    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }
}
