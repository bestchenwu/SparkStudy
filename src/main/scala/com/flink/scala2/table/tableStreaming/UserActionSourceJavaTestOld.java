package com.flink.scala2.table.tableStreaming;

import com.spark.common.util.CommonDateUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.table.api.*;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.api.java.Tumble;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sources.DefinedProctimeAttribute;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.types.Row;

import javax.annotation.Nullable;
import java.sql.Timestamp;

/**
 * flink流式表自定义时间属性
 * 参考例子:https://flink.sojb.cn/dev/table/streaming.html
 * 由于scala版本的{@link UserActionSource}无法调用写入到sink，所以改用java版本
 *
 * @author chenwu on 2020.1.8
 */
class UserActionSourceJavaOld implements StreamTableSource<Row>, DefinedProctimeAttribute {

    private String[] names ;
    private TypeInformation[] types ;

    public UserActionSourceJavaOld(String[] names, TypeInformation[] types){
        this.names = names;
        this.types = types;
    }

    @Nullable
    @Override
    public String getProctimeAttribute() {
        return "userActionTime";
    }

    @Override
    public DataStream<Row> getDataStream(StreamExecutionEnvironment env) {
//        Properties properties = new Properties();
//        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
//        properties.setProperty("group.id", "user-action-source");
//        FlinkKafkaConsumer<String> flinkKafkaConsumer = new FlinkKafkaConsumer<String>("test-210", new SimpleStringSchema(), properties);
//        DataStream<String> kafkaStream = env.addSource(flinkKafkaConsumer);
//        SingleOutputStreamOperator<Row> inputStream = kafkaStream.map((String str) -> {
//            String[] splitArray = str.split(",");
//            Row row = new Row(3);
//            row.setField(0, splitArray[0]);
//            row.setField(1, splitArray[1]);
//            long timestamp = Long.parseLong(splitArray[2]);
//            row.setField(2,new Timestamp(timestamp));
//            return row;
//        });
        DataStream<String> dataStream = env.fromElements("hello","sweet");
        DataStream<Row> inputStream = dataStream.map(new MapFunction<String, Row>() {
            @Override
            public Row map(String value) throws Exception {
                Row row = new Row(3);
                row.setField(0,value);
                row.setField(1,value);
                row.setField(2,new Timestamp(CommonDateUtil.getCurrentTimeStamp()));
                return row;
            }
        });
        SingleOutputStreamOperator<Row> rowSingleOutputStreamOperator = inputStream.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Row>() {
            @Override
            public long extractAscendingTimestamp(Row element) {
                Timestamp timestamp = (Timestamp)element.getField(2);
                return timestamp.getTime();
            }
        });
        return rowSingleOutputStreamOperator;
    }

    @Override
    public TypeInformation<Row> getReturnType() {
        return Types.ROW_NAMED(names,types);
    }

    @Override
    public TableSchema getTableSchema() {
        return new TableSchema(names,types);
    }
}

public class UserActionSourceJavaTestOld {

    private static final String[] names = new String[]{"username", "data","userActionTime"};
    private static final TypeInformation[] types = new TypeInformation[]{Types.STRING,Types.STRING, Types.SQL_TIMESTAMP};

    public static void main(String[] args) {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(2);
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
        tEnv.registerTableSource("UserActions",new UserActionSourceJavaOld(names,types));
        //tEnv.registerTableSource("UserActions", new UserActionSource());
        GroupWindowedTable windowedTable = tEnv
                .scan("UserActions")
                .window(Tumble.over("5.seconds").on("UserActionTime").as("userActionWindow"));
        windowedTable.table().select("data").writeToSink(new CsvTableSink("D:\\logs\\flinkSink\\userActions.csv", "\\|",1, FileSystem.WriteMode.OVERWRITE));
//        CsvTableSink csvTableSink = new CsvTableSink("D:\\logs\\flinkSink\\userActions.csv", "\\|");
//        csvTableSink = (CsvTableSink)csvTableSink.configure(new String[]{"row"},new TypeInformation[]{Types.ROW_NAMED(names,types)});
//        tEnv.registerTableSink("result",csvTableSink);
        //指定查询配置
       // StreamQueryConfig streamQueryConfig = tEnv.queryConfig();
        //最小空闲状态保持时间定义多久它被删除之前非活动键的状态至少保持
        //在最大空闲状态保持时间定义多久它被删除前的非激活键的状态最多保持
//        streamQueryConfig.withIdleStateRetentionTime(Time.hours(12),Time.hours(24));
//        windowedTable.table().insertInto("result",streamQueryConfig);
    }
}
