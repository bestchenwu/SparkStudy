package com.flink.scala2.table.tableStreaming;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.LocalStreamEnvironment;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.table.sources.DefinedRowtimeAttributes;
import org.apache.flink.table.sources.RowtimeAttributeDescriptor;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.table.sources.tsextractors.ExistingField;
import org.apache.flink.table.sources.wmstrategies.AscendingTimestamps;
import org.apache.flink.types.Row;
import org.junit.Test;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * http://apache-flink.147419.n8.nabble.com/Flink-1-9-1-sql-1-8-x-td1244.html源代码
 */
public class TestSql {

    @Test
    public void testAccountingSql() {
        LocalStreamEnvironment env = StreamExecutionEnvironment.createLocalEnvironment(1);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        try {
            StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

            SimpleTableSource source = new SimpleTableSource();
//            Table t = tableEnv.fromTableSource(source);
            tableEnv.registerTableSource("userActions",source);
            String interval = "5"; //5 second
            System.out.println("source schema is " + source.getTableSchema());

            Table sqlResult = tableEnv.sqlQuery("SELECT " +
                    " TUMBLE_START(UserActionTime, INTERVAL '" + interval + "' SECOND) as rowTime, " +
                    " Username," +
                    " SUM(Data) as Data " +
                    " FROM  userActions"+
                    " GROUP BY TUMBLE(UserActionTime, INTERVAL '" + interval + "' SECOND),Username");


            String[] fieldNames = {
                    "rowTime",
                    "Username", "Data"};
            TypeInformation[] fieldTypes = {
                    TypeInformation.of(Timestamp.class),
                    TypeInformation.of(String.class),
                    TypeInformation.of(Long.class)};

            TableSink sink1 = new CsvTableSink("d:/data.log", ",");

            sink1 = sink1.configure(fieldNames, fieldTypes);
            tableEnv.registerTableSink("EsSinkTable", sink1);
            System.out.println("SQL result schema is " + sqlResult.getSchema());

            tableEnv.sqlUpdate("insert into EsSinkTable select " +
                    " rowTime,Username,Data from " + sqlResult + "");

            env.execute("test");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("start program error. FlowMatrix --zookeeper <zookeeperAdress> --config <configpath>" +
                    " --name <jobName> --interval <intervalInMinute> --indexName <indexName>");
            System.err.println(e.toString());
            return;
        }
    }

    public static class SimpleTableSource implements StreamTableSource<Row>, DefinedRowtimeAttributes {
        @Override
        public DataStream<Row> getDataStream(StreamExecutionEnvironment env) {
            return env.fromCollection(genertateData()).assignTimestampsAndWatermarks(new AssignerWithPunctuatedWatermarks<Row>() {
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
        }

        @Override
        public TableSchema getTableSchema() {
            TableSchema schema = TableSchema.builder()
                    .field("Username", Types.STRING)
                    .field("Data", Types.LONG)
                    .field("UserActionTime", Types.SQL_TIMESTAMP)
                    .build();
            return schema;
        }

        @Override
        public TypeInformation<Row> getReturnType() {
            String[] names = new String[]{"Username", "Data", "UserActionTime"};
            TypeInformation[] types = new TypeInformation[]{Types.STRING, Types.LONG, Types.SQL_TIMESTAMP};
            return Types.ROW_NAMED(names, types);
        }


        @Override
        public List<RowtimeAttributeDescriptor> getRowtimeAttributeDescriptors() {
            RowtimeAttributeDescriptor rowtimeAttrDescr = new RowtimeAttributeDescriptor(
                    "UserActionTime",
                    new ExistingField("UserActionTime"),
                    new AscendingTimestamps());
            List<RowtimeAttributeDescriptor> listRowtimeAttrDescr = Collections.singletonList(rowtimeAttrDescr);
            return listRowtimeAttrDescr;
        }


        private static List<Row> genertateData() {
            List<Row> rows = new ArrayList<>();
            long startTime = System.currentTimeMillis() / 1000 - 10000;
            for (int i = 0; i < 10000; i++) {
                rows.add(buildRecord(startTime, i));
            }
            return rows;
        }

        private static Row buildRecord(long startTime, int i) {
            Row row = new Row(3);
            row.setField(0, "fox"); //Username
            row.setField(1, (long) Math.random() * 1000); //Data
            row.setField(2, new Timestamp((startTime + i)*1000)); //UserActionTime
            return row;
        }
    }
}
