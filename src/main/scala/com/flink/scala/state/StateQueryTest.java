package com.flink.scala.state;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.queryablestate.client.QueryableStateClient;
import org.apache.flink.api.common.JobID;
import java.util.concurrent.CompletableFuture;

public class StateQueryTest {

    public static void main(String[] args) throws Exception {
        String tmHostname = "";
        int proxyPort = 0;
        QueryableStateClient client = new QueryableStateClient(tmHostname, proxyPort);

// the state descriptor of the state to be fetched.
        ValueStateDescriptor<Tuple2<Long, Long>> descriptor =
                new ValueStateDescriptor<>(
                        "average",
                        TypeInformation.of(new TypeHint<Tuple2<Long, Long>>() {
                        }));
        JobID jobId = JobID.fromHexString("");
        Long key = 0l;
        CompletableFuture<ValueState<Tuple2<Long, Long>>> resultFuture =
                client.getKvState(jobId, "query-name", key, BasicTypeInfo.LONG_TYPE_INFO, descriptor);

// now handle the returned value
        resultFuture.thenAccept(response -> {
            try {
                Tuple2<Long, Long> res = response.value();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
