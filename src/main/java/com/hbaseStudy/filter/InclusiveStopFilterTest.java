package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.InclusiveStopFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * {@link org.apache.hadoop.hbase.filter.InclusiveStopFilter}的单元测试
 *
 * @author chenwu on 2020.3.15
 */
public class InclusiveStopFilterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        //到row22结束,含row22
        Filter filter = new InclusiveStopFilter(Bytes.toBytes("row22"));
        Map<String, String> map = hBaseClient.scanTableWithFilter(null, null, filter);
        System.out.println(map);
    }
}
