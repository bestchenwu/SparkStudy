package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * rowkey filter的测试类
 *
 * @author chenwu on 2020.3.15com/hbaseStudy/filter/RowkeyFilterTest.java:15
 */
public class RowkeyFilterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        //Filter rowFilter = new RowFilter(CompareOperator.GREATER_OR_EQUAL,new BinaryComparator(Bytes.toBytes("row2")));
        //只查询row2开头的
        //Filter rowFilter = new RowFilter(CompareOperator.EQUAL,new RegexStringComparator("row2[0-9]?"));
        //查询rowkey包含row2
        Filter rowFilter = new RowFilter(CompareOperator.EQUAL,new SubstringComparator("row2"));
        Map<String, String> map = hBaseClient.scanTableWithFilter("cf", "a", rowFilter);
        System.out.println(map);
    }
}
