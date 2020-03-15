package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.TimestampsFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * {@link org.apache.hadoop.hbase.filter.TimestampsFilter}的测试类
 *
 * @author chenwu on 2020.3.15
 */
public class TimeStampFilterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        //由于一个时间戳精准定位一个版本,所以这样可以精准一行一列
        Filter filter = new TimestampsFilter(Arrays.asList(1583589792651l,1584238775482l));
        Map<String, String> resultMap = hBaseClient.scanTableWithFilter(null, null, filter);
        System.out.println(resultMap);
    }
}
