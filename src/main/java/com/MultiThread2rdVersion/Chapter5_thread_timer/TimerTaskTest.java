package com.MultiThread2rdVersion.Chapter5_thread_timer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * {@link java.util.TimerTask}的使用
 *
 * @author chenwu on 2020.8.20
 */
class MyTimerTask extends TimerTask{

    @Override
    public void run() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        System.out.println("run time:"+localTime.format(dateTimeFormatter));
    }

}

public class TimerTaskTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        System.out.println("now time:"+localTime.format(dateTimeFormatter));

        LocalTime scheduleTime = localTime.plus(10, ChronoUnit.SECONDS);
        System.out.println("schedule time:"+scheduleTime.format(dateTimeFormatter));

        MyTimerTask myTimerTask = new MyTimerTask();
        Timer timer = new Timer();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.of(localDate,scheduleTime);
        Instant instant = localDateTime.atZone(zone).toInstant();
        timer.schedule(myTimerTask,Date.from(instant));

        /**
         * 将Date转换为LocalDateTime
         *
         * java.util.Date date = new java.util.Date();
         *     Instant instant = date.toInstant();
         *     ZoneId zone = ZoneId.systemDefault();
         *     LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
         *     LocalTime localTime = localDateTime.toLocalTime();
         */
    }
}
