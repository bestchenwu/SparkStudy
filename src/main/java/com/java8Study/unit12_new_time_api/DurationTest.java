package com.java8Study.unit12_new_time_api;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * 新time api
 *
 * @author chenwu on 2019.10.14
 */
public class DurationTest {

    public static void main(String[] args) {
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, 10, 10, 18, 38, 55);
        LocalDate localDate = LocalDate.parse("2019-10-12");
        LocalDateTime localDateTime2 = localDate.atTime(15, 18, 22);
        //Duration可以用来表示LocalDate、LocalTime、LocalDateTime的时间差
        //between表示第一个时间和第二个时间的差距,第二个时间比第一个时间大,才返回正数
        Duration duration = Duration.between(localDateTime2, localDateTime1);
        System.out.println(duration.getSeconds());
        //Period表示日期之间的差距
        LocalDate localDate1 = LocalDate.parse("2019-10-15");
        System.out.println(localDate1);
        Period period = Period.between(localDate, localDate1);
        System.out.println(period.getDays());
        //时间的增减法 获得2019-10-12的明天
        LocalDate tomorrow = localDate.plus(1l, ChronoUnit.DAYS);
        System.out.println(tomorrow);
        //等价于
        LocalDate tomorrow1 = localDate.plusDays(1l);
        System.out.println(tomorrow1);
    }
}
