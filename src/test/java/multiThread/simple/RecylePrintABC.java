package multiThread.simple;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实例化三个线程，一个线程打印a，一个线程打印b，一个线程打印c，三个线程同时执行,<br/>
 * 要求打印出10个连着的abc。
 *
 */
public class RecylePrintABC {

    public static final int MAX_PRINT_COUNT = 10;

    private static class PrintThread extends Thread{

        private ReentrantLock printLock;
        private Condition thisCondition;
        private Condition nextCondition;
        private String printChar;

        public PrintThread(ReentrantLock printLock,Condition thisCondition,Condition nextCondition,String printChar){
            this.printChar = printChar;
            this.printLock = printLock;
            this.thisCondition = thisCondition;
            this.nextCondition = nextCondition;
        }

        @Override
        public synchronized void run() {
            try{
                printLock.lock();
                for(int i = 0;i<MAX_PRINT_COUNT;i++){
                    System.out.println(printChar);
                    nextCondition.signal();
                    if(i<MAX_PRINT_COUNT-1){
                        try{
                            thisCondition.await();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }finally {
                printLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        //打印锁
        ReentrantLock printLock = new ReentrantLock();
        //线程A的锁
        Condition aCondition = printLock.newCondition();
        //线程B的锁
        Condition bCondition = printLock.newCondition();
        //线程C的锁
        Condition cCondition = printLock.newCondition();
        PrintThread aThread = new PrintThread(printLock,aCondition,bCondition,"A");
        PrintThread bThread = new PrintThread(printLock,bCondition,cCondition,"B");
        PrintThread cThread = new PrintThread(printLock,cCondition,aCondition,"C");
        aThread.start();
        Thread.sleep(100);
        bThread.start();
        Thread.sleep(200);
        cThread.start();
    }
}
