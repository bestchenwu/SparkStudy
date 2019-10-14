package com.java8Study.unit12_new_time_api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期类格式化
 *
 * @author chenwu on 2019.10.14
 */
public class FormatTest {

    public static void main(String[] args){
        LocalDate localDate = LocalDate.parse("2019-10-12");
        String result = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        //自定义格式化工具
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        result = localDate.format(dateTimeFormatter);
        System.out.println(result);
    }
}
