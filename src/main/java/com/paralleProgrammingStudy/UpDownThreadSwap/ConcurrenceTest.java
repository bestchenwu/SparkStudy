package com.paralleProgrammingStudy.UpDownThreadSwap;

public class ConcurrenceTest {

    private static final long count = 100*100l;

    public static void main(String[] args) throws InterruptedException {
        concurrence();
        serial();
    }

    private static void concurrence() throws InterruptedException{
            long start = System.currentTimeMillis();
            Thread thread = new Thread(){
                @Override
                public void run() {
                    int a = 0;
                    for(long i = 0;i<count;i++){
                        a+=5;
                    }
                }
            };
            thread.start();
            int b = 0;
            for(long i=0;i<count;i++){
                    b-=1;
            }
            long time = System.currentTimeMillis()-start;
            //等待thread死亡
            thread.join();
            System.out.println("concurrence time:"+time+",b="+b);
    }

    private static void serial() throws InterruptedException{
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i = 0;i<count;i++){
            a+=5;
        }
        int b = 0;
        for(long i=0;i<count;i++){
            b-=1;
        }
        long time = System.currentTimeMillis()-start;
        System.out.println("serial time:"+time+",b="+b);
    }
}
