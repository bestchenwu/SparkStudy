package com.flink.scala2.dataset.sample;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class SocketSample {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> text =
                env.socketTextStream("localhost", 1234);
        DataStream<Tuple2<String, Integer>> counts =
                text.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (value, out) -> {
                    String[] splitArray = value.split(",");
                    out.collect(Tuple2.of(splitArray[0], Integer.parseInt(splitArray[1])));
                })
                        .keyBy(0)
                        .sum(1);
        counts.print();
        env.execute("WordCount from SocketTextStream Example");
    }
}
