package com.zookeeperStudy.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link NumberTransferUtil}的单元测试类
 *
 * @author chenwu on 2019.11.23
 */
public class NumberTransferUtilTest {

    @Test
    public void testTransfer0xToOct() {
        String str = "400000012";
        Assert.assertEquals(17179869202l, NumberTransferUtil.transfer0xToOct(str));
    }
}
