package com.MultiThread2rdVersion.Chapter2_thread_synchronized;

public class SynchroinzedTest {

    /**
     * descriptor: ()V
     *     flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
     *     Code:
     *       stack=0, locals=0, args_size=0
     *          0: return
     *       LineNumberTable:
     *         line 5: 0
     *
     *  编译完成后会给方法打一个ACC_SYNCHRONIZED的标记,执行线程将先获取对象monitor,获取成功之后才能执行方法体,方法体执行完成后才释放monitor
     */
    //public static synchronized void test(){}

    /**
     *  3: monitorenter
     *          4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *          7: ldc           #3                  // String hello
     *          9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *         12: aload_1
     *         13: monitorexit
     *
     *  在指令码里面使用了monitorenter  monitorexit指令进行同步处理
     *  每个对象都有一个监视器，monitorenter为试图获得该对象的监视器，当线程获得监视器后，监视器monitor+1
     *  其他线程只有等monitor为0的时候才可以进入，同一个线程重复进入该对象，monitor的进入数+1
     *
     *  monitorexit指令执行的时候，监视器的monitor进入数-1，如果进入数为0，则线程退出monitor
     */
    public void test(){
        synchronized (this){
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        SynchroinzedTest a = new SynchroinzedTest();
        a.test();
    }
}
