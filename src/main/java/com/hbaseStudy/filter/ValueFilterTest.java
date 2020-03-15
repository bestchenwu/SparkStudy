package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.ValueFilter;

import java.io.IOException;
import java.util.Map;

/**
 * {@link org.apache.hadoop.hbase.filter.ValueFilter}的测试
 *
 * @author chenwu on 2020.3.15
 */
public class ValueFilterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        Filter valueFilter = new ValueFilter(CompareOperator.EQUAL,new RegexStringComparator("value3[0-9]?"));
        Map<String, String> resultMap = hBaseClient.scanTableWithFilter(null, null, valueFilter);
        System.out.println(resultMap);
    }
}
