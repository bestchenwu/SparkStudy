package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * {@link org.apache.hadoop.hbase.filter.PrefixFilter}的单元测试
 *
 * @author chenwu on 2020.3.15
 */
public class PrefixFilterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        //只匹配rowkey为row2开头的
        Filter filter = new PrefixFilter(Bytes.toBytes("row2"));
        Map<String, String> resultMap = hBaseClient.scanTableWithFilter(null, null, filter);
        System.out.println(resultMap);
    }

}
