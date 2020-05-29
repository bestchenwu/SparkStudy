package com.flink.scala2.table.tableStreaming.userDefined.functions

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.table.api.scala.BatchTableEnvironment
import org.apache.flink.table.functions.{ScalarFunction, TableFunction}
import org.apache.flink.api.scala._
import org.apache.flink.table.api.scala._
import org.apache.flink.types.Row

/**
  * 自定义hashCode函数
  *
  * @param factor
  * @author chenwu on 2020.5.29
  */
class HashCode(factor: Int) extends ScalarFunction {

  def eval(s: String): Int = {
    s.hashCode() * factor
  }
}

/**
  * 将一行元素转为多行
  *
  * @param seperator 分隔符
  * @author chenwu on 2020.5.29
  */
class Split(seperator: String) extends TableFunction[(String, Int)] {

  def eval(s: String) = {
    s.split(seperator).foreach(item => collect(item, item.length))
  }
}

object UserDefinedScalaFunctionTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(2)
    val tableEnv = BatchTableEnvironment.create(env)
    val dataSet = env.fromElements(("sweet#love", 1), ("jack#mary", 2), ("sweet#baby", 3))
    val table = tableEnv.fromDataSet(dataSet, 'name, 'data)
    //以下为测试 一个字段转换为一个字段的情况
    //    val hashCode =  new HashCode(5)
    //    tableEnv.registerFunction("hashCode", new HashCode(5));
    //    tableEnv.registerTable("myTable", table)
    //    //val queryResult = tableEnv.sqlQuery("select name,hashCode(name) as hashCode from myTable")
    //    //或者
    //    val queryResult = table.select('name, hashCode('name))
    //以下为测试 一个字段转换为多个字段的情况
    val split = new Split("#")
    val queryResult = table.joinLateral(split('name) as('word, 'length)).select('name, 'word, 'length)

    /**
      * 输出:
      *
      * sweet#love,sweet,5
      * sweet#love,love,4
      * jack#mary,jack,4
      * jack#mary,mary,4
      * sweet#baby,sweet,5
      * sweet#baby,baby,4
      */
    queryResult.toDataSet[Row].print()
  }
}
