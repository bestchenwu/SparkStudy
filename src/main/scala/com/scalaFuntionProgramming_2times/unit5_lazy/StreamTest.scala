package com.scalaFuntionProgramming_2times.unit5_lazy

object StreamTest {

  def main(args: Array[String]): Unit = {
    //val stream = Stream.apply(1, 2, 3, 4, 5, 6)
    //测试take函数
    //    val tak3Stream = stream.take(3)
    //    val resultList = tak3Stream.toList
    //    println(resultList)
    //测试drop函数
    //    val drop3Stream = stream.drop(3)
    //    val resultList = drop3Stream.toList
    //    println(resultList)
    //测试takeWhile
    //    val result = stream.takeWhile(_ % 2 == 1).toList
    //    println(result)
    //测试exists
    //    val result = stream.exists(_ == 8)
    //    println(result)
    //测试forall
    //      val result = stream.forAll(_<4)
    //      println(result)
    //      val result = Stream.ones.take(3).toList
    //      println(result)

    //        val result = Stream.constant(33).take(5).toList
    //        println(result)
    //    val result = Stream.from(3).take(5).toList
    //    println(result)
//
//    val result = Stream.fibs.take(10).toList
//    println(result)

//    var x = 3
//    val f: (String) => Option[(Int, String)] = (y: String) => if (y.length > 3) None else {
//      val option = Some((x, y + x))
//      x =x*2
//      option
//    }
//
//    val unfoldStream = Stream.unfold1("a")(f)
//    val resultList = unfoldStream.toList
//    println(resultList)

//    val onesByFold = Stream.onesViaUnfold.take(5).toList
//    println(onesByFold)

//    val list = Stream.fromIntViaFold(10).take(5).toList
//    println(list)

    val list = Stream.fibsViaFold.take(10).toList
    println(list)
  }
}
