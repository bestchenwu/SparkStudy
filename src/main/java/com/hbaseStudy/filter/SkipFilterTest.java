package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.SkipFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * {@link org.apache.hadoop.hbase.filter.SkipFilter}的测试
 *
 * @author chenwu on 2020.3.15
 */
public class SkipFilterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        Filter filter = new QualifierFilter(CompareOperator.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("a")));
        //skipFilter的意思就是当一行数据满足了filter(filter为true),那么该行的其他列也一起跳过
        Filter skipFilter = new SkipFilter(filter);
        Map<String, String> map = hBaseClient.scanTableWithFilter(null, null, skipFilter);
        System.out.println(map);
    }
}
