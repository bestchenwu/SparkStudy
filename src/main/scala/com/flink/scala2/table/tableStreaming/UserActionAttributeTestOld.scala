//package com.flink.scala2.table.tableStreaming
//
//import java.util.Properties
//
//import org.apache.flink.api.common.serialization.SimpleStringSchema
//import org.apache.flink.api.common.typeinfo.TypeInformation
//import org.apache.flink.api.scala.typeutils.Types
//import org.apache.flink.streaming.api.datastream.DataStream
//import org.apache.flink.api.scala._
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
//import org.apache.flink.table.api.TableSchema
//import org.apache.flink.table.sources.{DefinedProctimeAttribute, StreamTableSource}
//import org.apache.flink.types.Row
//import org.apache.kafka.clients.consumer.ConsumerConfig
//import org.apache.spark.api.java.function.MapFunction
//
//class UserActionSource extends StreamTableSource[Row] with DefinedProctimeAttribute{
//
//  val fieldNames = Array[String]("name","data")
//  val fieldTypes = Array[TypeInformation[_]](Types.STRING,Types.INT)
//
//  override def getDataStream(execEnv: StreamExecutionEnvironment): DataStream[Row] = {
//      val properties = new Properties()
//      properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
//      properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"flinkTest")
//      val flinkConsumerSource = new FlinkKafkaConsumer[String]("test-flink",new SimpleStringSchema(),properties)
//      //val env = StreamExecutionEnvironment.createLocalEnvironment(2)
//      val flinkStream = execEnv.addSource(flinkConsumerSource)
////    flinkStream.map(new MapFunction[String,Row] {
////      override def call(value: String): Row = {
////
////      }
////    })
////      flinkStream.map(new MapFunction[String,Row] {
////        override def call(value: String): Row = {
////            null
////        }
////      })
//      null
//  }
//
//  override def getProctimeAttribute: String = {
//      "UserActionTime"
//  }
//
//  override def getReturnType: TypeInformation[Row] = {
//      Types.ROW(fieldNames,fieldTypes)
//  }
//
//  override def getTableSchema: TableSchema = {
//      new TableSchema(fieldNames,fieldTypes)
//  }
//}
//
//object UserActionAttributeTest {
//  def main(args: Array[String]): Unit = {
//
//  }
//}
