package com.flink.scala2.operaters

import org.apache.flink.streaming.api.functions.co.CoMapFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation
/**
  * 两个流之间connect
  *
  * @author chenwu on 2019.12.13
  */
object ConnectStreamTest {

  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.createLocalEnvironment(1)
      val stringStream = env.fromCollection(Seq("a","b","c"))
      val intStream = env.fromCollection(Seq(1,2,3))
      val booleanStream = stringStream.connect(intStream).map(new CoMapFunction[String,Int,Boolean] {
        override def map1(value: String):Boolean = {
            value.compareTo("b") match{
              case 1=>true
              case _=>false
            }
        }

        override def map2(value: Int): Boolean = {
            value.compareTo(3) match{
              case 1 =>true
              case _=>false
            }
        }
      })
      booleanStream.print()
      env.execute("ConnectStreamTest")
  }
}
