package com.flink.scala2.table.tableStreaming

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.scala.StreamTableEnvironment

/**
  * 自定义table format
  *
  * @author chenwu on 2020.5.26
  */
object TableKafkaSourceMyFormatTest {

  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.createLocalEnvironment(2)
      val tableEnv = StreamTableEnvironment.create(env)
      
  }
}
