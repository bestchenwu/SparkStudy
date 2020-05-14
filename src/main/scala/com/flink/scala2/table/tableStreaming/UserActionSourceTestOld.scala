package com.flink.scala2.table.tableStreaming

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.common.typeinfo.{BasicTypeInfo, TypeInformation}
import org.apache.flink.streaming.api.{datastream, environment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.table.api.{TableSchema, Tumble, Types}
import org.apache.flink.table.sources.{DefinedProctimeAttribute, StreamTableSource}
import org.apache.flink.types.Row
import org.apache.flink.streaming.api.scala.{createTypeInformation, StreamExecutionEnvironment => ScalaStreamExecutionEnvironment}
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.sinks.CsvTableSink


/**
  * flink流式表自定义时间属性
  * 参考例子:https://flink.sojb.cn/dev/table/streaming.html
  *
  * @author chenwu on 2020.1.6
  */
class UserActionSourceOld extends StreamTableSource[Row] with DefinedProctimeAttribute {

  val names = Array[String]("username", "data")
  val types = Array[TypeInformation[_]](BasicTypeInfo.STRING_TYPE_INFO, BasicTypeInfo.STRING_TYPE_INFO)

  override def getDataStream(env: environment.StreamExecutionEnvironment): datastream.DataStream[Row] = {
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
    properties.setProperty("group.id", "user-action-source")
    val flinkKafkaConsumer = new FlinkKafkaConsumer[String]("test-210", new SimpleStringSchema(), properties)
    val scalaStreamExecutionEnvironment = new ScalaStreamExecutionEnvironment(env)
    val kafkaStream = scalaStreamExecutionEnvironment.addSource(flinkKafkaConsumer)
    val rowStream = kafkaStream.map(item => {
      val array = item.split(",")
      val row = new Row(2)
      row.setField(0, array(0))
      row.setField(0, array(1))
      row
    })
    rowStream.javaStream
  }

  //定义处理时间属性
  override def getProctimeAttribute: String = "UserActionTime"

  override def getReturnType: TypeInformation[Row] = {
    Types.ROW(names, types)
  }

  override def getTableSchema: TableSchema = {
    new TableSchema(names, types)
  }
}

object UserActionSourceTest {
  def main(args: Array[String]): Unit = {
    val env = ScalaStreamExecutionEnvironment.createLocalEnvironment(1)
    val tableEnv = StreamTableEnvironment.create(env)
    tableEnv.registerTableSource("UserActions", new UserActionSourceOld)
    val csvTableSink = new CsvTableSink("D:\\logs\\flinkSink\\UserActionTime.log", "\\|")
    tableEnv.registerTableSink("result",csvTableSink)
    val windowTable = tableEnv.scan("UserActions").window(Tumble.over("10.minutes").on("UserActionTime").as("userActionWindow"))
    //val windowTable = tableEnv.scan("UserActions").window(Tumble over 10.minutes on 'UserActionTime as 'userActionWindow)
    //windowTable.table
    //todo:这里的Table怎么使用呢?调用.table提示table是私有域,无法访问

  }
}
