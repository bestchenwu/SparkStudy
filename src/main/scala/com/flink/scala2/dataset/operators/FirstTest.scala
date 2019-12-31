package com.flink.scala2.dataset.operators

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment, createTypeInformation}
import org.apache.flink.util.Collector

/**
  *
  */
object FirstTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val input1: DataSet[(Int, String)] = env.fromElements((1, "d"), (1, "bbb"), (2, "ff"))
    val input2: DataSet[(String, Int)] = env.fromElements(("aa", 1), ("cc", 1), ("ee", 2))
    val result = input1.join(input2).where(0).equalTo(1) {
      (item1, item2, out: Collector[(Int, String)]) => {
        out.collect(item1)
        out.collect((item2._2, item2._1))
      }
    }
    val firstDataSet = result.groupBy(0).sortGroup(1, Order.ASCENDING).first(1)
    firstDataSet.print()
    //输出
    //由于aa比d小,所以这里输出了1 aa
    //(1,"aa"),(2,"ee")
  }
}
