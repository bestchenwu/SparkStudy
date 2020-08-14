package com.MultiThread2rdVersion.Chapter4_thread_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService{

    private Lock lock = new ReentrantLock();
}

public class LockTest {


}
