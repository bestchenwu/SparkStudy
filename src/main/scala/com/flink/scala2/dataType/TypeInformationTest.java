package com.flink.scala2.dataType;

import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * flink的TypeInformation测试
 *
 * @author chenwu on 2020.1.3
 */
public class TypeInformationTest {

    public static void main(String[] args) {
//        TypeInformation info0 = TypeInformation.of(new TypeHint<String>() {
//        });
        TypeInformation info = TypeInformation.of(new TypeHint<Tuple2<Long, Long>>() {
        });
//        TypeInformation info1 = TypeInformation.of(new TypeHint<com.flink.scala2.watermark.MyEvent>() {
//        });
    }
}
