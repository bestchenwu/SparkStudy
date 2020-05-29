//package com.flink.scala2.table.tableStreaming.userDefined.functions

//todo:用scala实现AggregateFunction真困难
//
//import java.lang.{Integer => JInteger, Long => JLONG}
//
//import org.apache.flink.api.java.tuple.{Tuple1 => JTuple1}
//import org.apache.flink.table.functions.AggregateFunction
//
//class WeightAvgAccum extends JTuple1[Long,Integer]{
//    var sum = 0l
//    var count = 0
//}
//
//class WeightAvgAccumFunction extends AggregateFunction[Long,Integer]{
//  override def createAccumulator(): WeightAvgAccum = {
//
//  }
//
//  override def getValue(accumulator: WeightAvgAccum): JLONG = {
//      0l
//  }
//}
//
//
//object UserDefinedAggregateFunctionTest {
//
//  def main(args: Array[String]): Unit = {
//
//  }
//}
