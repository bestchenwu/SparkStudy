package com.flink.scala2.dataStream.state;

import org.apache.flink.api.common.JobID;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.api.common.typeutils.TypeSerializerSnapshot;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.memory.DataInputView;
import org.apache.flink.core.memory.DataOutputView;
import org.apache.flink.queryablestate.client.QueryableStateClient;
import org.apache.flink.util.Collector;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;


/**
 * 自定义序列化
 */
class CustomerTypeSerializer extends TypeSerializer<Tuple2<Long,Long>>{
    @Override
    public boolean isImmutableType() {
        return false;
    }

    @Override
    public TypeSerializer<Tuple2<Long, Long>> duplicate() {
        return null;
    }

    @Override
    public Tuple2<Long, Long> createInstance() {
        return null;
    }

    @Override
    public Tuple2<Long, Long> copy(Tuple2<Long, Long> from) {
        return null;
    }

    @Override
    public Tuple2<Long, Long> copy(Tuple2<Long, Long> from, Tuple2<Long, Long> reuse) {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public void serialize(Tuple2<Long, Long> record, DataOutputView target) throws IOException {

    }

    @Override
    public Tuple2<Long, Long> deserialize(DataInputView source) throws IOException {
        return null;
    }

    @Override
    public Tuple2<Long, Long> deserialize(Tuple2<Long, Long> reuse, DataInputView source) throws IOException {
        return null;
    }

    @Override
    public void copy(DataInputView source, DataOutputView target) throws IOException {

    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public TypeSerializerSnapshot<Tuple2<Long, Long>> snapshotConfiguration() {
        return null;
    }
}

/**
 * 支持状态查询的flapFlapFunction
 *
 * @author chenwu on 2019.12.10
 */
class CountWindowCoverage extends RichFlatMapFunction<Tuple2<Long, Long>, Tuple2<Long, Long>> {

    private ValueState<Tuple2<Long, Long>> sum;

    @Override
    public void flatMap(Tuple2<Long, Long> value, Collector<Tuple2<Long, Long>> out) throws Exception {
        Tuple2<Long, Long> tuple2 = sum.value();
        if (tuple2 == null) {
            tuple2 = new Tuple2<>(0l, 0l);
        }
        tuple2.f0 += 1;
        tuple2.f1 += value.f1;
        sum.update(tuple2);
        if (tuple2.f1 >= 2) {
            out.collect(tuple2);
            sum.clear();
        }
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        ValueStateDescriptor<Tuple2<Long, Long>> descriptor = new ValueStateDescriptor<Tuple2<Long, Long>>("average", TypeInformation.of(new TypeHint<Tuple2<Long, Long>>() {

        }));
        descriptor.setQueryable("CountWindowCoverage");
        sum = getRuntimeContext().getState(descriptor);
    }
}

/**
 * 将状态做成可查询
 * 要在Flink群集上启用可查询状态，只需将Flink分发flink-queryable-state-runtime_2.11-1.7-SNAPSHOT.jar 的opt/文件夹复制 到该文件夹即可。
 * 否则，未启用可查询状态函数。lib/
 * 要验证群集是否在启用了可查询状态的情况下运行，请检查该行的任何TaskManager的日志："Started the Queryable State Proxy Server @ ..."。
 *
 * @author chenwu on 2019.12.10
 */
public class QueryableState {

    public static void main(String[] args) {
        try{
            //这里指定主机的名称
            QueryableStateClient queryableStateClient = new QueryableStateClient("127.0.0.1",65188);
            ValueStateDescriptor<Tuple2<Long, Long>> descriptor = new ValueStateDescriptor<Tuple2<Long, Long>>("average", TypeInformation.of(new TypeHint<Tuple2<Long, Long>>() {

            }));
            CompletableFuture<ValueState<Tuple2<Long, Long>>> kvState = queryableStateClient.getKvState(JobID.fromHexString("a8fe284a7938e2d88fab732f791e943d"), "CountWindowCoverage", 1l, BasicTypeInfo.LONG_TYPE_INFO, descriptor);
            kvState.thenAccept(response -> {
                try{
                    System.out.println(response.value());
                }catch(IOException e){
                    e.printStackTrace();
                }

            });
        }catch(UnknownHostException e){
            throw new RuntimeException(e);
        }


    }
}
