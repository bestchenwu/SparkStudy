package com.flink.scala2.dataset.operators

import org.apache.flink.api.scala.{ExecutionEnvironment, createTypeInformation}
import org.apache.flink.util.Collector

/**
  * flink combine group的测试类
  *
  * @author chenwu on 2019.12.31
  */
object GroupCombineTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val dataStream = env.fromElements("hello", "word", "hello")
    //combineGroup允许输入的源类型和输出的类型不一致
    val combineGroupStream = dataStream.groupBy(item=>item).combineGroup {
    //val combineGroupStream = dataStream.groupBy(0).combineGroup {
      (iter: Iterator[String], out: Collector[(String, Int)]) => {
        var key: String = ""
        var count: Int = 0
        for (word <- iter) {
          key = word
          count += 1
        }
        out.collect(key, count)
      }
    }
    combineGroupStream.print()
    println("------")
    val dataStream1 = env.fromElements(("hello", 1), ("word", 1), ("word", 1))
    val reduceGroupStream = dataStream1.groupBy(0).reduceGroup { iter => {
      var key: String = ""
      var sum: Int = 0
      for ((word, count) <- iter) {
        key = word
        sum += count
      }
      (key, sum)
    }
    }
    reduceGroupStream.print()
  }
}
