package com.flink.scala.table

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.expressions.ExpressionParser

object TableQueryTest {
  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.createLocalEnvironment(2)
      val tableEnv = StreamTableEnvironment.create(env)
      val element1 = env.fromElements((1,"hello"))
      val element2  = env.fromElements((1,"hello"))
      val fields = ExpressionParser.parseExpressionList("count,word")
      val table1 = tableEnv.fromDataStream(element1,fields:_*)
      val table2 = tableEnv.fromDataStream(element2,fields:_*)
      val table = table1.where("LIKE(word,'F%')").unionAll(table2)
      val explanation = tableEnv.explain(table)
      println(explanation)
    //val table1 = env.fromElements((1,"hello")).toTable(tableEnv,"count,word")
  }
}
