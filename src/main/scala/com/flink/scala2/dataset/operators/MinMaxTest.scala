package com.flink.scala2.dataset.operators

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment,createTypeInformation}
import org.apache.flink.api.java.aggregation.Aggregations._

/**
  * flink 内置聚合函数 min minBy max maxBy的测试
  *
  * @author chenwu on 2019.12.31
  */
object MinMaxTest {
  def main(args: Array[String]): Unit = {
      val env = ExecutionEnvironment.createLocalEnvironment(1)
      val input:DataSet[(Int,String,Double)] = env.fromElements((11,"abc",1.1d),(22,"abc",0.8d),(33,"abc",1.5d))
      //按第1个字段聚合,对第0个求和,对第二个字段求最大值
      val result = input.groupBy(1).aggregate(SUM,0).and(MAX,2)
      result.print()
      //输出:(66,abc,1.5)
      //按第1个字段聚合 ,依次优先取第0个字段和第2个字段的最小值
      val result1 = input.groupBy(1).minBy(0,2)
      result1.print()
      //输出(11,abc,1.1)
  }
}
