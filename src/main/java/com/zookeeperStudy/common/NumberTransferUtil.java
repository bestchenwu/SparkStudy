package com.zookeeperStudy.common;


/**
 * 不同进制间的数字转换
 *
 * @author chenwu on 2019.11.23
 */
public class NumberTransferUtil {

    /**
     * 将十六进制转换为十进制
     *
     * @param number_0x
     * @return long
     * @author chenwu on 2019.11.23
     */
    public static long transfer0xToOct(String number_0x){
         return Long.parseLong(number_0x,16);
    }
}
