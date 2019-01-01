package com.spark.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用的日期工具类
 *
 * @author chenwu on 2019.1.1
 */
public class CommonDateUtil {

    private static String PRECISION_TO_SECONDS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将日期转换为带秒的时间字符串
     *
     * @param date
     * @return {@link String}
     * @author chenwu on 2019.1.1
     */
    public static String parseDateToStringWithSeconds(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(PRECISION_TO_SECONDS);
        String dateStr = format.format(date);
        return dateStr;
    }
}


