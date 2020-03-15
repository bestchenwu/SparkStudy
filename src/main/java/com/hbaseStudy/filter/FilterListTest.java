package com.hbaseStudy.filter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * 组合使用filter
 */
public class FilterListTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
        Filter rowkeyFilter = new RowFilter(CompareOperator.EQUAL,new BinaryComparator(Bytes.toBytes("row3")));
        Filter qualifierFilter = new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator(Bytes.toBytes("c")));
        //FilterList其实继承了FilterListBase,再间接继承FilterBase
        FilterList filterList = new FilterList();
        filterList.addFilter(rowkeyFilter);
        filterList.addFilter(qualifierFilter);
        Map<String, String> map = hBaseClient.scanTableWithFilter(null, null, filterList);
        System.out.println(map);
    }
}
