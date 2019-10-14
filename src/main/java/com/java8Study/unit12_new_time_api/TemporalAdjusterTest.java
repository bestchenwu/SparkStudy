package com.java8Study.unit12_new_time_api;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.*;

/**
 *  使用Java8的时间调整器TemporalAdjusters
 *
 * @author chenwu on 2019.10.14
 */
public class TemporalAdjusterTest {

    public static void main(String[] args){
        LocalDate localDate = LocalDate.of(2019,10,10);
        //LocalDate localDate1 = localDate.with(nextOrSame(DayOfWeek.MONDAY));
        //输出2019-10-14 因为当天就是星期一
        //System.out.println(localDate1);
        //使用自定义的工具类,获取下一个工作日
        LocalDate nextWorkDay = localDate.with(new NextWorkingDay());
        System.out.println(nextWorkDay);
    }
}
