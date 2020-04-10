package com.flink.scala2.table

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.scala.BatchTableEnvironment
import org.apache.flink.table.api.{TableConfig, TableEnvironment}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.scala._

/**
  * flink table sample
  *
  * @author chenwu on 2020.1.3
  */
object TableSample {

  def main(args: Array[String]): Unit = {
    //流处理的形式
    val streamEnv = StreamExecutionEnvironment.createLocalEnvironment()
    val tableStreamEnv = StreamTableEnvironment.create(streamEnv)
    //批处理的形式
    val env = ExecutionEnvironment.createLocalEnvironment(2)
    val tableConfig = new TableConfig()
    val tableEnv = BatchTableEnvironment.create(env, tableConfig)
    val table1 = env.fromElements((1, "word")).toTable(tableEnv, 'count, 'word)
    val table2 = env.fromElements((2, "ffword")).toTable(tableEnv, 'count, 'word)
    val resultTable = table1.unionAll(table2).where('word like "%f")
    val explain = tableEnv.explain(resultTable)
    print(explain)
  }
}
