package com.MultiThread2rdVersion.Chapter7_thread_extra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 验证{@link java.text.SimpleDateFormat}的线程安全性
 *
 * @author chenwu on 2020.8.24
 */
class SimpleDateThread extends Thread {

    private SimpleDateFormat simpleDateFormat;
    private String dateString;

    public SimpleDateThread(SimpleDateFormat simpleDateFormat, String dateString) {
        this.simpleDateFormat = simpleDateFormat;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            SimpleDateFormat threadSafeSimpleDateFormat = DateTools.getSimpleDateFormat();
            Date date = threadSafeSimpleDateFormat.parse(dateString);
            //Date date = this.simpleDateFormat.parse(dateString);
            //String targetDateString = this.simpleDateFormat.format(date);
            String targetDateString = threadSafeSimpleDateFormat.format(date);
            if (!dateString.equals(targetDateString)) {
                System.out.println(dateString + " != " + targetDateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

class DateTools{

    //用ThreadLocal来代替
    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();

    public static SimpleDateFormat getSimpleDateFormat(){
        SimpleDateFormat dateFormat = simpleDateFormatThreadLocal.get();
        if(dateFormat==null){
            System.out.println("empty dateFormat:"+Thread.currentThread().getName());
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormatThreadLocal.set(dateFormat);
        }
        return dateFormat;
    }
}

public class SimpleDateFormatterTest {

    public static void main(String[] args) {
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
//        String format = dateFormat.format(date);
//        System.out.println(format);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{"2000-01-01", "2000-01-02",
                "2000-01-03", "2000-01-04", "2000-01-05", "2000-01-06",
                "2000-01-07", "2000-01-08", "2000-01-09", "2000-01-10"};
        SimpleDateThread[] threadArray = new SimpleDateThread[dateStringArray.length];
        for (int i = 0; i < dateStringArray.length; i++) {
            String dateString = dateStringArray[i];
            threadArray[i] = new SimpleDateThread(dateFormat, dateString);
            threadArray[i].setName("thread-"+i);
        }
        for (SimpleDateThread simpleDateThread : threadArray) {
            simpleDateThread.start();
        }
    }
}
