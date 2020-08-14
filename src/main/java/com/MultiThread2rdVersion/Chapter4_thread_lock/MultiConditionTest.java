package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockService1{

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
}

public class MultiConditionTest {


}
