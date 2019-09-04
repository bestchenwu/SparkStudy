package com.flink.scala.state;

import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.scala.DataStream;
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment;
import scala.Function1;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.JavaConversions;

import java.util.ArrayList;
import java.util.Arrays;

public class CountWindowQueryTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(2);
        TypeInformation info = TypeInformation.of(new TypeHint<Tuple2<Long,Long>>() {
        });
        ArrayBuffer<Tuple2<Long,Long>> seq = new ArrayBuffer<Tuple2<Long,Long>>(10);
        java.util.ArrayList<Tuple2<Long,Long>> list = new ArrayList();
        list.add(Tuple2.of(1L, 3L));
        list.add(Tuple2.of(1L, 5L));
        list.add(Tuple2.of(1L, 7L));
        list.add(Tuple2.of(1L, 4L));
        list.add(Tuple2.of(1L, 2L));
        //Iterator iter = JavaConversions.asScalaIterator(list.iterator());
        //DataStream datastream = env.fromCollection(iter,info);
        Buffer buffer1 = JavaConversions.asScalaBuffer(list);
        DataStream datastream = env.fromElements(buffer1,info);
        Buffer buffer = JavaConversions.asScalaBuffer(Arrays.asList(1));
        DataStream flatMapDataStream = datastream.keyBy(new KeySelector<Tuple2<Long,Long>,Long>() {
            @Override
            public Long getKey(Tuple2<Long, Long> value) throws Exception {
                return value.f0;
            }
        }, info).flatMap(new CountWindowQueryAverage(),info);
        flatMapDataStream.print();
        env.execute("CountWindowQueryTest");

    }
}
