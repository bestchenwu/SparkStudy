package com.hbaseStudy.counter;

import com.hbaseStudy.common.HBaseClient;
import org.apache.hadoop.hbase.client.Increment;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Pair;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * hbase counter的测试类
 *
 * @author chenwu on 2020.3.21
 */
public class HBaseCounterTest {

    public static void main(String[] args) throws IOException {
        HBaseClient hBaseClient = new HBaseClient("hbase/local-hbase-site.xml", "counters");
        //单条增加
        //        long currentValue = hBaseClient.increaseValue("20200321","weekly","click",33L);
//        System.out.println("currentValue1="+currentValue);
//        currentValue = hBaseClient.increaseValue("20200321","weekly","click",21L);
//        System.out.println("currentValue2="+currentValue);
        //批量增加
        Increment increment = new Increment(Bytes.toBytes("20200322"));
        increment.addColumn(Bytes.toBytes("daily"),Bytes.toBytes("click"),55L);
        increment.addColumn(Bytes.toBytes("daily"),Bytes.toBytes("click"),21L);
        increment.addColumn(Bytes.toBytes("weekly"),Bytes.toBytes("exps"),32L);
        List<Pair<String,Long>> result = hBaseClient.batchIncreaseIncrement(increment);
        System.out.println(result);
    }
}
