package com.flink.scala.stream.source;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.scala.DataStream;
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment;
public class SeqTest {

    public static void main(String[] args) {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Object> objectStream = environment.generateSequence(1l,100l).filter(new FilterFunction<Object>() {
            @Override
            public boolean filter(Object value) throws Exception {
                if(value==null || !(value instanceof Long)){
                    return false;
                }else{
                    Long value1 = (Long)value;
                    return value1%7==0;
                }
            }
        });
//        objectStream.map(new MapFunction<Object, Object>() {
//            @Override
//            public Object map(Object value) throws Exception {
//                return null;
//            }
//        },createTypeInformation)
    }
}
