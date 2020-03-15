package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * {@link org.apache.hadoop.hbase.filter.SingleColumnValueFilter}的测试
 * 这种只对单行值进行过滤
 *
 * @author chenwu on 2020.3.15
 */
public class SingleColumnValueFilterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes("cf"),Bytes.toBytes("a"), CompareOperator.NOT_EQUAL,Bytes.toBytes("value21"));
        Map<String, String> map = hBaseClient.scanTableWithFilter(null, null, filter);
        System.out.println(map);
    }
}
