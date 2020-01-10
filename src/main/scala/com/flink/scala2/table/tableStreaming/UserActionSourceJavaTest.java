package com.flink.scala2.table.tableStreaming;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.*;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.api.java.Tumble;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sources.DefinedProctimeAttribute;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.types.Row;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * flink流式表自定义时间属性
 * 参考例子:https://flink.sojb.cn/dev/table/streaming.html
 * 由于scala版本的{@link UserActionSource}无法调用写入到sink，所以改用java版本
 *
 * @author chenwu on 2020.1.8
 */
class UserActionSourceJava implements StreamTableSource<Row>, DefinedProctimeAttribute {

    private String[] names = new String[]{"username", "data","userActionTime"};
    private TypeInformation[] types = new TypeInformation[]{BasicTypeInfo.STRING_TYPE_INFO, BasicTypeInfo.STRING_TYPE_INFO,Types.SQL_TIMESTAMP};

    @Nullable
    @Override
    public String getProctimeAttribute() {
        return "userActionTime";
    }

    @Override
    public DataStream<Row> getDataStream(StreamExecutionEnvironment env) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("group.id", "user-action-source");
        FlinkKafkaConsumer<String> flinkKafkaConsumer = new FlinkKafkaConsumer<String>("test-210", new SimpleStringSchema(), properties);
        DataStream<String> kafkaStream = env.addSource(flinkKafkaConsumer);
        SingleOutputStreamOperator<Row> rowStream = kafkaStream.map((String str) -> {
            String[] splitArray = str.split(",");
            Row row = new Row(3);
            row.setField(0, splitArray[0]);
            row.setField(1, splitArray[1]);
            long timestamp = Long.parseLong(splitArray[2]);
            row.setField(2,new Timestamp(timestamp));
            return row;
        });
        return rowStream;
    }

    @Override
    public TypeInformation<Row> getReturnType() {
        return Types.ROW_NAMED(names, types);
    }

    @Override
    public TableSchema getTableSchema() {
        return new TableSchema(names, types);
    }
}

public class UserActionSourceJavaTest {

    public static void main(String[] args) {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(2);
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
        tEnv.registerTableSource("UserActions",new UserActionSourceJava());
        //tEnv.registerTableSource("UserActions", new UserActionSource());
        GroupWindowedTable windowedTable = tEnv
                .scan("UserActions")
                .window(Tumble.over("5.seconds").on("UserActionTime").as("userActionWindow"));
        CsvTableSink csvTableSink = new CsvTableSink("D:\\logs\\flinkSink\\userActions.csv", "\\|");
        tEnv.registerTableSink("result",csvTableSink);
        windowedTable.table().insertInto("result");
        //指定查询配置
        StreamQueryConfig streamQueryConfig = tEnv.queryConfig();
        //最小空闲状态保持时间定义多久它被删除之前非活动键的状态至少保持
        //在最大空闲状态保持时间定义多久它被删除前的非激活键的状态最多保持
        streamQueryConfig.withIdleStateRetentionTime(Time.hours(12),Time.hours(24));
        windowedTable.table().insertInto("result",streamQueryConfig);
    }
}
