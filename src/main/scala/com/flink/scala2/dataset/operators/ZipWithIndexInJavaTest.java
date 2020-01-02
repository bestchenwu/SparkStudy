package com.flink.scala2.dataset.operators;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.LocalEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.DataSetUtils;

/**
 * flink的zipWithIndex测试
 *
 * @author chenwu on 2020.1.2
 */
public class ZipWithIndexInJavaTest {

    public static void main(String[] args) throws  Exception{
        LocalEnvironment localEnvironment = ExecutionEnvironment.createLocalEnvironment(2);
        DataSource<String> input = localEnvironment.fromElements("a", "b", "c", "d","e","f","g");
        DataSet<Tuple2<Long, String>> tuple2DataSet = DataSetUtils.zipWithIndex(input);
        tuple2DataSet.print();
        System.out.println("-------");
        //不需要连续分配标签,加快标签分配过程
        DataSet<Tuple2<Long, String>> tuple2DataSet1 = DataSetUtils.zipWithUniqueId(input);
        tuple2DataSet1.print();
        //两次输出的结果都是如下
        /**
         * (0,a)
         * (1,b)
         * (2,c)
         * (3,d)
         * (4,e)
         * (5,f)
         * (6,g)
         */

    }
}
