package com.flink.scala2.dataset.operators

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment, createTypeInformation}
import org.apache.flink.util.Collector

/**
  * flink的join测试
  *
  * @author chenwu on 2019.12.31
  */
object JoinTest {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(1)
    val input1: DataSet[(Int, String)] = env.fromElements((1, "a"), (2, "b"), (4, "ff"))
    val input2: DataSet[(String, Int)] = env.fromElements(("dd", 1), ("c", 2), ("ee", 3))
    val result = input1.join(input2).where(0).equalTo(1)

    /**
      * 输出
      * ((1,a),(dd,1))
      * ((2,b),(cc,2))
      */
    result.print()
    println("------")
    //flat join 输出零个或者一个或者多个数据元
    //原书例子
    //    case class Rating(val name:String,category:String,points:Int)
    //    val ratings: DataSet[Rating] = null
    //    val weights: DataSet[(String, Double)] = null
    //
    //    ratings.join(weights).where("category").equalTo(0){
    //      (rating, weight, out: Collector[(String, Double)]) =>
    //        if (weight._2 > 0.1) out.collect(rating.name, rating.points * weight._2)
    //    }
    val flatJoinResult = input1.join(input2).where(0).equalTo(1) {
      (item1, item2, out: Collector[(String, Int)]) => {
        if (item2._1.length < 2) {
          out.collect(item2)
        }
      }
    }
    flatJoinResult.print()
    //输出(c,2)
    //left join
    println("------")
    val leftJoinDataSet = input1.leftOuterJoin(input2).where(0).equalTo(1){
      (item1,item2,out:Collector[(String,Int)])=>{
          if(item2==null){
            out.collect((item1._2,item1._1))
          }else{
            out.collect(item2._1,item1._1)
          }
      }
    }
    leftJoinDataSet.print()
    //输出:
//    (dd,1)
//    (c,2)
//    (ff,4)
  }
}
