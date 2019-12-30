package com.flink.scala2.dataset.operators

import org.apache.flink.api.common.functions.{FilterFunction, RichFilterFunction}
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.configuration.Configuration

/**
  * 将参数传递给flink函数
  *
  * @author chenwu on 2019.12.30
  */
object FilterWithConfigurationTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val dataSet = env.fromElements(1, 2, 3, 4, 5)
    val conf = new Configuration()
    conf.setInteger("limit", 2)
    val resultSet = dataSet.filter(new RichFilterFunction[Int] {

      var limit: Int = 0

      override def open(parameters: Configuration): Unit = {
        limit = parameters.getInteger("limit", 0)
      }

      override def filter(value: Int): Boolean = {
        value > limit
      }

    }).withParameters(conf)
    resultSet.print()
  }
}
