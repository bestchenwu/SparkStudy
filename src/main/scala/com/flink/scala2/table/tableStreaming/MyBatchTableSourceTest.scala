package com.flink.scala2.table.tableStreaming

import com.flink.scala2.table.tableStreaming.userDefined.MyBatchTableSource
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.scala.BatchTableEnvironment

/**
  * [[com.flink.scala2.table.tableStreaming.userDefined.MyBatchTableSource]]的测试类
  *
  * @author chenwu on 2020.5.28
  */
object MyBatchTableSourceTest {

  def main(args: Array[String]): Unit = {
      val env = ExecutionEnvironment.createLocalEnvironment(2)
      val tableEnv = BatchTableEnvironment.create(env)
      tableEnv.registerTableSource("my-source",new MyBatchTableSource())
  }
}
