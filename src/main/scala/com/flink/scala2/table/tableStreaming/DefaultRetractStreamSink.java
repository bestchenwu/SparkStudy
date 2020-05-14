package com.flink.scala2.table.tableStreaming;

import org.apache.commons.io.IOUtils;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.types.Row;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 用于动态变化流的fileSink
 *
 * @author chenwu on 2020.5.14
 */
public class DefaultRetractStreamSink extends RichSinkFunction<Tuple2<java.lang.Boolean, Row>> {

    private String fileName;
    private boolean isOverWrite;
    private FileOutputStream fos;
    private BufferedWriter bw;

    public DefaultRetractStreamSink(String fileName,boolean isOverwrite){
        this.fileName = fileName;
        this.isOverWrite = isOverWrite;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        fos = new FileOutputStream(fileName, isOverWrite);
        bw = new BufferedWriter(new OutputStreamWriter(fos));
    }

    @Override
    public void invoke(Tuple2<Boolean, Row> value, Context context) throws Exception {
        if(value==null){
            return ;
        }
        boolean flag = value.f0;
        //这里先只实现数据增加的情况
        if(flag){
            //表明这是一条增加的数据
            Row row = value.f1;
            bw.write(row.toString()+"\n");
            bw.flush();
        }else{
            //表明这是一条删除的数据
        }
    }

    @Override
    public void close() throws Exception {
        super.close();
        IOUtils.closeQuietly(bw);
        IOUtils.closeQuietly(fos);
    }
}
