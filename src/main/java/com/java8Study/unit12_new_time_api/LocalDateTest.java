package com.java8Study.unit12_new_time_api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

/**
 * java8 新的时间API
 *
 * @author chenwu on 2019.10.14
 */
public class LocalDateTest {

    public static void main(String[] args) {
        //新日期API的使用方法
//        LocalDate localDate = LocalDate.of(2019,10,14);
//        System.out.println("year:"+localDate.getYear());
//        System.out.println("day of week:"+localDate.getDayOfWeek().getValue());
//        LocalDate now = LocalDate.now();
//        System.out.println("now:"+now.getYear());
//        System.out.println("day of now:"+now.getDayOfWeek().getValue());
        //新时分秒(以下统称为时间)的使用方式
//         LocalTime localTime = LocalTime.of(23,11,55);
//         System.out.println("minute:"+localTime.getMinute());
        LocalDate localDate = LocalDate.parse("2019-10-12");
        System.out.println(localDate);

        LocalTime localTime = LocalTime.parse("12:22:55");
        System.out.println(localTime);
        //将日期和时间合并
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, 10, 10, 18, 38, 55);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
        //或者在LocalDate上调用atTime来获取
        LocalDateTime localDateTime2 = localDate.atTime(15, 18, 22);
        System.out.println(localDateTime2);

    }
}
