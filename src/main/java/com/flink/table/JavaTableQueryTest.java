package com.flink.table;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.table.api.Table;
//import org.apache.flink.table.api.TableEnvironment;
//import org.apache.flink.table.api.java.StreamTableEnvironment;

public class JavaTableQueryTest {

    public static void main(String[] args){

//        StreamExecutionEnvironment env =  StreamExecutionEnvironment.createLocalEnvironment();
//        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
//        DataStream<Tuple2<Integer, String>> stream1 = env.fromElements(new Tuple2<>(1,"hello"));
//        DataStream<Tuple2<Integer, String>> stream2 = env.fromElements(new Tuple2<>(1, "hello"));
//        Table table1 = tableEnv.fromDataStream(stream1,"count,word");
//        Table table2= tableEnv.fromDataStream(stream2,"count,word");
//        Table table = table1.where("LIKE(word,'F%')").unionAll(table2);
//        String explanation = tableEnv.explain(table);
//        System.out.println(explanation);
    }
}
