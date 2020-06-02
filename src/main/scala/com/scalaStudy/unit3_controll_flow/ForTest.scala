package com.scalaStudy.unit3_controll_flow

/**
  * scala for循环测试
  */
object ForTest extends App {

  //    val list = List("ab","cd")
  //    val capList = for(item <- list) yield item.capitalize
  //    println(capList)
  //
  //    for((item,index)<-list.zipWithIndex){
  //        println(s"index=$index,item=$item")
  //    }

  //以下代码用于测试scala 对于for循环的编译解释过程
//  for (i <- 0 until 10 if i % 2 == 1) {
//    println(s"oddNumber=$i")
//  }
  //执行scalac -Xprint:parse ForTest.scala 输出内容为
  //0.until(10).withFilter(((i) => i.$percent(2).$eq$eq(1))).foreach(((i) => println(StringContext("oddNumber=", "").s(i))))
  //从这里可以看出until 是在RichInt上调用的函数 返回的Range,执行的if实际上调用的是Range里面的withFilter函数

  //对于多行foreach语句，建议使用如下方式:
  for{
      i <- 0 to 10
      j <-1 to 5
      if i%2 == 1 && j ==3
  } println(s"i=$i,j=$j")
}
