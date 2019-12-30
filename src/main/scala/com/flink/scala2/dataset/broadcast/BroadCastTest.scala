package com.flink.scala2.dataset.broadcast

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.configuration.Configuration
import scala.collection.JavaConversions._

/**
  * flink 广播变量的测试
  *
  * @author chenwu on 2019.12.30
  */
object BroadCastTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val toBroadCast = env.fromElements(1, 2, 3)
    val data = env.fromElements("a", "b")
    val mapStream = data.map(new RichMapFunction[String, String] {

      var broadCastSeq: Traversable[Integer] = null

      override def open(parameters: Configuration): Unit = {
        broadCastSeq = getRuntimeContext.getBroadcastVariable[Integer]("broadCastDataset")
      }

      override def map(value: String): String = {
        broadCastSeq.map(numberValue => value + numberValue).mkString(",")
      }
    }).withBroadcastSet(toBroadCast, "broadCastDataset")
    mapStream.print()
  }
}
