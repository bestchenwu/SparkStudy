package com.flink.scala2.table.tableStreaming;

import com.spark.constants.SymbolConstants;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.GroupWindowedTable;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.api.java.Tumble;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sources.DefinedProctimeAttribute;
import org.apache.flink.table.sources.DefinedRowtimeAttributes;
import org.apache.flink.table.sources.RowtimeAttributeDescriptor;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.table.sources.tsextractors.ExistingField;
import org.apache.flink.table.sources.wmstrategies.AscendingTimestamps;
import org.apache.flink.types.Row;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.spark.metrics.sink.CsvSink;
import reactor.util.function.Tuples;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 自定义流式表的数据源<br/>
 * 参考:http://apache-flink.147419.n8.nabble.com/Flink-1-9-1-sql-1-8-x-td1244.html
 *
 * @author chenwu on 2020.5.14
 */

/**
 * procTime 处理时间流
 */
class ProcTimeUserActionAttribute implements StreamTableSource<Row>, DefinedProctimeAttribute {

    private String[] names = new String[]{"name", "data", "UserActionTime"};
    private TypeInformation[] types = new TypeInformation[]{Types.STRING, Types.INT, Types.SQL_TIMESTAMP};

    @Nullable
    @Override
    public String getProctimeAttribute() {
        return "UserActionTime";
    }

    @Override
    public DataStream<Row> getDataStream(StreamExecutionEnvironment execEnv) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "flink-test-group");
        FlinkKafkaConsumer<String> flinkKafkaConsumer = new FlinkKafkaConsumer<>("test-flink", new SimpleStringSchema(), properties);
        DataStreamSource<String> flinkDataStream = execEnv.addSource(flinkKafkaConsumer);
        SingleOutputStreamOperator<Row> outputStream = flinkDataStream.map(new MapFunction<String, Row>() {
            @Override
            public Row map(String value) throws Exception {
                String[] splitArray = value.split(SymbolConstants.SYMBOL_DH);
                String name = splitArray[0];
                Integer data = Integer.parseInt(splitArray[1]);
                Long timeMills = System.currentTimeMillis();
                //return Row.of(name,data);
                Timestamp timestamp = new Timestamp(timeMills);
                return Row.of(name, data, timestamp);
            }
        }).returns(Types.ROW_NAMED(names, types));
        return outputStream;
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

/**
 * eventTime 事件时间流
 */
class RowTimeUserActionAttribute implements StreamTableSource<Row>, DefinedRowtimeAttributes {

    private String[] names = new String[]{"name", "data", "UserActionTime"};
    private TypeInformation[] types = new TypeInformation[]{Types.STRING, Types.INT, Types.SQL_TIMESTAMP};
//    private String[] names = new String[]{"name","data"};
//    private TypeInformation[] types = new TypeInformation[]{Types.STRING,Types.INT};

//    @Nullable
//    @Override
//    public String get() {
//        return "UserActionTime";
//    }


    @Override
    public List<RowtimeAttributeDescriptor> getRowtimeAttributeDescriptors() {
        RowtimeAttributeDescriptor rowtimeAttrDescr = new RowtimeAttributeDescriptor(
                "UserActionTime",
                new ExistingField("UserActionTime"),
                new AscendingTimestamps());
        List<RowtimeAttributeDescriptor> listRowtimeAttrDescr = Collections.singletonList(rowtimeAttrDescr);
        return listRowtimeAttrDescr;
    }

    @Override
    public DataStream<Row> getDataStream(StreamExecutionEnvironment execEnv) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "flink-test-group");
        FlinkKafkaConsumer<String> flinkKafkaConsumer = new FlinkKafkaConsumer<>("test-flink", new SimpleStringSchema(), properties);
        DataStreamSource<String> flinkDataStream = execEnv.addSource(flinkKafkaConsumer);
        SingleOutputStreamOperator<Row> outputStream = flinkDataStream.map(new MapFunction<String, Row>() {
            @Override
            public Row map(String value) throws Exception {
                String[] splitArray = value.split(SymbolConstants.SYMBOL_DH);
                String name = splitArray[0];
                Integer data = Integer.parseInt(splitArray[1]);
                Long timeMills = System.currentTimeMillis();
                //return Row.of(name,data);
                Timestamp timestamp = new Timestamp(timeMills);
                return Row.of(name, data, timestamp);
            }
        });
        SingleOutputStreamOperator<Row> outputStreamWithTimpeStamp = outputStream.assignTimestampsAndWatermarks(new AssignerWithPunctuatedWatermarks<Row>() {
            private long lastWaterMarkMillSecond = -1;
            private long waterMarkPeriodMillSecond = 1000;

            @Nullable
            @Override
            public Watermark checkAndGetNextWatermark(Row lastElement, long extractedTimestamp) {
                if (extractedTimestamp - lastWaterMarkMillSecond >= waterMarkPeriodMillSecond) {
                    lastWaterMarkMillSecond = extractedTimestamp;
                    return new Watermark(extractedTimestamp);
                }
                return null;
            }

            @Override
            public long extractTimestamp(Row element, long previousElementTimestamp) {
                return ((Timestamp) element.getField(2)).getTime();
            }
        });
        return outputStreamWithTimpeStamp;
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

public class UserActionAttributeTest {

    public static void main(String[] args) throws Exception {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(1);
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        //env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);
        tEnv.registerTableSource("userActions", new ProcTimeUserActionAttribute());
//        String interval = "5"; //5 second
//        Table resultTable = tEnv.sqlQuery("SELECT " +
//                " TUMBLE_START(UserActionTime, INTERVAL '" + interval + "' SECOND) as rowTime, " +
//                " name," +
//                " SUM(data) as dataSum " +
//                " FROM  userActions"+
//                " GROUP BY TUMBLE(UserActionTime, INTERVAL '" + interval + "' SECOND),name");
        GroupWindowedTable windowedTable = tEnv.scan("userActions").window(Tumble.over("10.seconds").on("UserActionTime").as("userActionWindow"));
        Table resultTable = windowedTable.table().groupBy("name").select("name,data.sum as dataSum");
//        String[] tableNames = new String[]{"rowTime","name","dataSum"};
////        TypeInformation[] tableTypes = new TypeInformation[]{Types.SQL_TIMESTAMP,Types.STRING,Types.INT};
        String[] tableNames = new String[]{"name", "dataSum"};
        TypeInformation[] tableTypes = new TypeInformation[]{Types.STRING, Types.INT};
        MyRetractStreamSink myRetractStreamSink = new MyRetractStreamSink(tableNames, tableTypes,"D:\\logs\\flinkSink\\nameAge.csv",true);
        tEnv.registerTableSink("nameAgeSink", tableNames, tableTypes, myRetractStreamSink);
        resultTable.insertInto("nameAgeSink");
        env.execute("UserActionAttributeTest");
    }
}
