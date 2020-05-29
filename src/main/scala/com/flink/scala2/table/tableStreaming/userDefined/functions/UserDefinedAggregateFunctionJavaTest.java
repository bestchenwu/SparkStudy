package com.flink.scala2.table.tableStreaming.userDefined.functions;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.functions.AggregateFunction;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * Java版本的聚合函数
 *
 * @author chenwu on 2020.5.29
 */
class WeightAvgSum {
    public long sum = 0l;
    public int count = 0;
}

/**
 * 第一个参数类型表示返回的类型,第二个参数WeightAvgSum表示聚合时候的上下文变量
 *
 * @author chenwu on 2020.5.29
 */
class WeightAvgSumFunction extends AggregateFunction<Long, WeightAvgSum> {
    @Override
    public WeightAvgSum createAccumulator() {
        return new WeightAvgSum();
    }

    @Override
    public Long getValue(WeightAvgSum accumulator) {
        return accumulator.count != 0 ? accumulator.sum / accumulator.count : 0;
    }

    /**
     * 将新来的数据加入到聚合器WeightAvgSum中
     *
     * @param accumulator
     * @param value 值
     * @param weight 出现的次数
     * @author chenwu on 2020.5.29
     */
    public void accumulate(WeightAvgSum accumulator, long value,long weight) {
        accumulator.count += weight;
        accumulator.sum += value*weight;
    }

    /**
     * 合并iterable
     *
     * @param accumulator
     * @param iterable
     * @author chenwu on 2020.5.29
     */
    public void merge(WeightAvgSum accumulator, java.lang.Iterable<WeightAvgSum> iterable){
        for(WeightAvgSum weightAvgSum:iterable){
            accumulator.sum+=weightAvgSum.sum;
            accumulator.count+=accumulator.count;
        }
    }

    /**
     * 将已经包含的值减掉
     *
     * @param accumulator
     * @param value
     * @author chenwu on 2020.5.29
     */
    public void retract(WeightAvgSum accumulator, long value,long weight){
        accumulator.sum-=value*weight;
        accumulator.count-=weight;
    }

    /**
     * 重置
     *
     * @param accumulator
     * @author chenwu on 2020.5.29
     */
    public void resetAccumulator(WeightAvgSum accumulator){
        accumulator.sum = 0l;
        accumulator.count = 0;
    }

}

public class UserDefinedAggregateFunctionJavaTest {

    public static void main(String[] args) {
        LocalStreamEnvironment localEnvironment = StreamExecutionEnvironment.createLocalEnvironment(2);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(localEnvironment);
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"test-aggregate-group");

        new FlinkKafkaConsumer<String>("",new SimpleStringSchema(),props);
    }
}
