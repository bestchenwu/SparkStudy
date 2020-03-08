package com.hbaseStudy.dataModel;

import com.hbaseStudy.common.HBaseClient;

import java.util.Arrays;
import java.util.Map;

/**
 * 批量读hbase的测试
 *
 * @author chenwu on 2020.3.8
 */
public class MultiReadTest {

    public static void main(String[] args) throws Exception{
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        Map<String, String> resultMap = hBaseClient.multiGet(Arrays.asList("row1","row2","row6"), "cf", "a");
        System.out.println(resultMap);

    }
}
