package com.flink.scala2.table.tableStreaming

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.table.api.scala._

/**
  * 查看flink sql执行计划
  *
  * @author chenwu on 2020.5.12
  */
object TableExplainTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val tableEnv = StreamTableEnvironment.create(env)
    val table1 = env.fromElements((1, "hello")).toTable(tableEnv,'id1, 'name1)
    val table2 = env.fromElements((1, "jack")).toTable(tableEnv,'id2, 'name2)
    //val outputTable = table1.where('name.like("%F")).unionAll(table2)
    //inner join的例子
    //val outputTable = table1.join(table2).where('id1=='id2).select('id1,'name2)
    //lefjoin
    val outputTable = table1.leftOuterJoin(table2).where('id1=='id2).select('id1,'name2)
    val outputStream = tableEnv.toRetractStream[(Int,String)](outputTable)
    val result = outputStream.map(_._2._1>5)
    val explain = tableEnv.explain(outputTable)
    println(explain)
  }
}
