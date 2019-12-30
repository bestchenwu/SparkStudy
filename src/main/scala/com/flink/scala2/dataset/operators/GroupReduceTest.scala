package com.flink.scala2.dataset.operators

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala.{ExecutionEnvironment, createTypeInformation}
import org.apache.flink.util.Collector

/**
  * 对dataset先进行group 再进行reduce(聚合)
  *
  * @author chenwu on 2019.12.30
  */
object GroupReduceTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val wordStream = env.fromElements[(Int, String)]((2, "d"), (1, "a"), (2, "b"),(2,"d"), (1, "c"),(1,"c"))
    val result = wordStream.groupBy(0).sortGroup(1, Order.ASCENDING).reduceGroup {
      (in, out: Collector[(Int, String)]) => {
        var prev: (Int, String) = null
        for (t <- in) {
          if (prev == null || prev != t) {
            out.collect(t)
            prev = t
          }
        }
      }
    }
    result.print()
  }
}
