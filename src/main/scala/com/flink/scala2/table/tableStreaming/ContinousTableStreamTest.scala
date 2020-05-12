package com.flink.scala2.table.tableStreaming

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.table.api.scala._
import org.apache.flink.table.api.Tumble
import org.apache.flink.table.sinks.TableSink
import org.apache.flink.types.Row

/**
  * 表的持续流查询
  *
  * @author chenwu on 2020.5.12
  */
object ContinousTableStreamTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)
    val tableEnv = StreamTableEnvironment.create(env)
    val dataStream = env.fromElements(("sweet", 11), ("jack", 23), ("sweet", 34), ("jack", 41), ("haha", 55))
    //UserActionTime.proctime表示flink处理时间
    val table = tableEnv.fromDataStream(dataStream, 'username, 'data, 'UserActionTime.proctime)
    val windowTable = table.window(Tumble over 10.seconds on 'UserActionTime as 'UserActionWindow)
    val sumWindowTable = windowTable.groupBy('UserActionWindow,'username).select('username, 'data.sum)
    val outputStream = tableEnv.toRetractStream[(String,Integer)](sumWindowTable).map(_._2)
    outputStream.print()
    env.execute("ContinousTableStreamTest")
  }
}
