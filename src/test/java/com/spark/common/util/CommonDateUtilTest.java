package com.spark.common.util;

import org.junit.Test;

import java.util.Date;

public class CommonDateUtilTest {

    @Test
    public void testParseDateToStringWithSeconds() {
        Date date = new Date();
        System.out.print(CommonDateUtil.parseDateToStringWithSeconds(date));
    }
}
