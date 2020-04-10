package com.flink.scala2.table.tableStreaming

import java.util.Properties

import com.spark.constants.SymbolConstants
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.common.typeinfo.Types
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.table.api.scala.{StreamTableEnvironment}
import org.apache.flink.table.sinks.{CsvTableSink}
import org.apache.flink.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.kafka.clients.consumer.ConsumerConfig

/**
  * flink table的流写入到csv
  *
  * @author chenwu on 2020.4.9
  */
object KafkaToCsvStream {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    val tableEnv = StreamTableEnvironment.create(env)
    //批处理
    //val env = ExecutionEnvironment.createLocalEnvironment(2)
    //val tableEnv = BatchTableEnvironment.create(env)
    val props = new Properties()
    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"kafka-to-csv")
    val kafkaSource = new FlinkKafkaConsumer[String]("test-210", new SimpleStringSchema(), props)
    val kafkaStream = env.addSource(kafkaSource).map(item => {
      println(item)
      val strArray = item.split(SymbolConstants.SYMBOL_DH)
      (strArray(0), strArray(1))
    })
    val dataStream = env.fromElements(("test","raoshanshan"),("jack","haha"))
    //val result = tableEnv.fromDataStream(dataStream,'userId,'data)
    val result = tableEnv.fromDataStream(kafkaStream,'userId,'data)
    val sink = new CsvTableSink("D:\\logs\\flinkSink\\userActions.csv", "|")
    val fieldNames = Array("userId", "data")
    val fieldTypes: Array[TypeInformation[_]] = Array(Types.STRING, Types.STRING)
    tableEnv.registerTableSink("csvTable", fieldNames, fieldTypes, sink)
    result.insertInto("csvTable")
    env.execute("KafkaToCsvStream")
  }
}
