package com.flink.scala2.table.tableStreaming

import java.lang

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.tuple
import org.apache.flink.api.java.typeutils.RowTypeInfo
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.table.sinks.{RetractStreamTableSink, TableSink, TableSinkBase}
import org.apache.flink.types.Row

//class MyRetractStreamDefaultSink extends RichSinkFunction[tuple.Tuple2[lang.Boolean, Row]] {
//
//
//
////  override def invoke(value: tuple.Tuple2[lang.Boolean, Row], context: SinkFunction.Context[_]): Unit = {
////    if (value != null) {
////      val flag = value.f0
////      if (flag) {
////        println("add value:" + value.f1);
////      } else {
////        println("delete value:" + value.f1);
////      }
////    }
////  }
//  override def invoke(value: tuple.Tuple2[lang.Boolean, Row], context: SinkFunction.Context[_]): Unit = {
//
//  }
//}

class MyRetractStreamSink(var names: Array[String], var types: Array[TypeInformation[_]], sinkFunction: SinkFunction[tuple.Tuple2[lang.Boolean, Row]]) extends RetractStreamTableSink[Row] {

  def this(names: Array[String], types: Array[TypeInformation[_]],fileName:String,isOverwrite:Boolean) {
    this(names, types, new DefaultRetractStreamSink(fileName,isOverwrite));
  }

  override def getRecordType: TypeInformation[Row] = {
    new RowTypeInfo(types,names)
  }

  override def getFieldNames: Array[String] = names

  override def getFieldTypes: Array[TypeInformation[_]] = types

  override def configure(fieldNames: Array[String], fieldTypes: Array[TypeInformation[_]]): TableSink[tuple.Tuple2[lang.Boolean, Row]] = {
    this.names=fieldNames
    this.types=fieldTypes
    this
  }

  override def emitDataStream(dataStream: DataStream[tuple.Tuple2[lang.Boolean, Row]]): Unit = {
    dataStream.addSink(sinkFunction)
  }
}
