package com.flink.scala2.table.tableStreaming;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.api.java.typeutils.TupleTypeInfo;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.sinks.RetractStreamTableSink;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.table.sinks.TableSinkBase;
import org.apache.flink.types.Row;

public class MyRetractStreamSinkJava implements RetractStreamTableSink<Row> {

    private TableSchema tableSchema;
    private TypeInformation<Row> rowTypeInformation;
    private RichSinkFunction<Tuple2<Boolean,Row>> mySinkFunction;

    public MyRetractStreamSinkJava(String[] fieldNames, TypeInformation[] types, RichSinkFunction<Tuple2<Boolean,Row>> sinkFunction){
        this.tableSchema = new TableSchema(fieldNames,types);
        this.rowTypeInformation = new RowTypeInfo(tableSchema.getFieldTypes(),tableSchema.getFieldNames());
        if(sinkFunction!=null){
            mySinkFunction = sinkFunction;
        }else{
            mySinkFunction = new MyRetractDefaultSinkFunction();
        }
    }

    @Override
    public TypeInformation<Row> getRecordType() {
        return rowTypeInformation;
    }

    @Override
    public void emitDataStream(DataStream<Tuple2<Boolean, Row>> dataStream) {
        dataStream.addSink(mySinkFunction);
    }

    @Override
    public String[] getFieldNames() {
        return tableSchema.getFieldNames();
    }

    @Override
    public TypeInformation<?>[] getFieldTypes() {
        return tableSchema.getFieldTypes();
    }

    @Override
    public TupleTypeInfo<Tuple2<Boolean, Row>> getOutputType() {
        return new TupleTypeInfo(Types.BOOLEAN, rowTypeInformation);
    }

    @Override
    public TableSink<Tuple2<Boolean, Row>> configure(String[] fieldNames, TypeInformation<?>[] fieldTypes) {
        return null;
    }

    class MyRetractDefaultSinkFunction extends RichSinkFunction<Tuple2<Boolean,Row>>{
        @Override
        public void invoke(Tuple2<Boolean, Row> value, Context context) throws Exception {
            if(value == null){
                return;
            }
            Boolean flag = value.f0;
            if(flag){
                //增加
                System.out.println("add value:"+value.f1);
            }else{
                //删除
                System.out.println("delete value:"+value.f1);
            }
        }
    }
}


