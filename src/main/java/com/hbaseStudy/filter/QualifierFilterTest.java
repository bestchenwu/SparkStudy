package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * {@link org.apache.hadoop.hbase.filter.QualifierFilter}的测试
 *
 * @author chenwu on 2020.3.15
 */
public class QualifierFilterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        Filter filter = new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator(Bytes.toBytes("c")));
        Map<String, String> resultMap = hBaseClient.scanTableWithFilter(null, null, filter);
        System.out.println(resultMap);
    }
}
