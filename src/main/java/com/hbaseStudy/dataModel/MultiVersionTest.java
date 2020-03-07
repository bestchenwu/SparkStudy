package com.hbaseStudy.dataModel;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.util.Pair;
import org.apache.hadoop.hbase.util.Triple;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * 多个row版本的测试
 *
 * @author chenwu on 2020.3.7
 */
public class MultiVersionTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "test");
//        List<Triple<String, String, Long>> rowkeyValueVersionList = hBaseClient.queryWithMultiVersions("row1", "cf", "a", 3);
//        rowkeyValueVersionList.stream().forEach(item -> System.out.println("key=" + item.getFirst() + ",value=" + item.getSecond() + ",timestamp=" + item.getThird()));
            hBaseClient.addNewColumnFamily("cg");
            hBaseClient.updateColumnFamily("cg");
    }
}
