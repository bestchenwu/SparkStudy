package com.common;

import org.junit.Test;

/**
 * {@link CommonDateUtil}的单元测试类
 *
 * @author chenwu on 2019.12.25
 */
public class CommonDateUtilTest {

    @Test
    public void testGetNowTimeBySeconds() {
        String str = CommonDateUtil.getNowTimeBySeconds();
        System.out.println(str);
    }
}
