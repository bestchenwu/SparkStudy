package com.flink.scala.state;

import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.TupleTypeInfo;
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment;
import scala.collection.Seq;
import scala.collection.convert.WrapAsJava;
import scala.collection.convert.WrapAsScala;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.IndexedSeq;

import java.util.Arrays;
import java.util.List;

public class CountWindowQueryTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // this can be used in a streaming program like this (assuming we have a StreamExecutionEnvironment env)
        //env.fromCollection(Arrays.asList(Tuple2.of(1L, 3L), Tuple2.of(1L, 5L), Tuple2.of(1L, 7L), Tuple2.of(1L, 4L), Tuple2.of(1L, 2L)))
        String[] strs = new String[]{
                "hello",
                "world"
        };
        TypeInformation info = TypeInformation.of(new TypeHint<Tuple2<Long,Long>>() {
        });
        ArrayBuffer<Tuple2<Long,Long>> seq = new ArrayBuffer<Tuple2<Long,Long>>(10);

        env.fromElements(seq, info);
        ///env.fromElements(Tuple2<Long,Long>.of(1L, 3L), Tuple2.of(1L, 5L), Tuple2.of(1L, 7L), Tuple2.of(1L, 4L), Tuple2.of(1L, 2L))

//                .flatMap(new CountWindowQueryAverage());
//        operator.print();

    }
}
