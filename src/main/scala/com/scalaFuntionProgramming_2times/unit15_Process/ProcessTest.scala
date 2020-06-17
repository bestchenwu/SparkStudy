package com.scalaFuntionProgramming_2times.unit15_Process

import com.scalaFuntionProgramming_2times.unit15_Process.Process._

object ProcessTest {

  def main(args: Array[String]): Unit = {
    //          val f = (x:Int)=>x*2
    //          val processIO = liftOne(f)
    //         //val processIO = lift(f)
    //          val stream = Stream(1,3,5)
    //          val outStream = processIO(stream)
    //          println(outStream.toList)
    //    val doubleStream = Stream(1.0, 3.0, 5.0, 8.9)
    //    val process1 = mean
    //    val process2 = liftOne[Double,Double](_+1.0)
    //    val resultProcess = process1 ++ process2
    //    val list = resultProcess(doubleStream).toList
    //    println(list)
    ////    val list = sum(doubleStream).toList
    ////    println(list)
    //      val list = take(2)(doubleStream).toList
    //    println(list)

    val intsStream = Stream(1, 3, 41, 53, 87, 9, 103)
    //    val evenProcess = takeWhile[Int](_ % 2 == 0)
    ////    val list = evenProcess(intsStream).toList
    ////    println(list)
    //    val evenProcess = dropWhile[Int](_ % 2 == 0)
    //    val list = evenProcess(intsStream).toList
    //    println(list)
    //    val countProcess = count[Int]
    //    val ints = countProcess(intsStream).toList
    //    println(ints)
    //    val meanList = mean(doubleStream).toList
    //    println(meanList)
    val list = exists[Int](_ % 2 == 0)(intsStream).toList
    println(list)
  }
}
