package com.scalaStudy.unit3_controll_flow

import scala.annotation.switch

object SwitchTest {

  def main(args: Array[String]): Unit = {
    //    val i = 1
    //    val x = (i: @switch) match {
    //      case 1 => "one"
    //      case 2 => "two"
    //      case _ => "other"
    //    }

    //给switch case 模式匹配的语句加一个变量
    //    val list = List(1, 2, 3)
    //    val result = list match {
    //      //_表示任意一个元素 _*表示0个或者任意多个元素
    //      case oneList@List(1, _*) => oneList.sum
    //      case twoList@List(2, _) => twoList.foldRight(1)((a, b) => a * b)
    //    }
    //    println(result)

    //对列表类型的元素递归调用
    val list2 = List("a", "b", "c")

    def listToString(strList: List[String]): String = strList match {
      case str :: other => str + " " + listToString(other)
      case Nil => ""
    }


    def listMultiPly(intList:List[Int]):Int = intList match {
      case num :: other => num*listMultiPly(other)
      case Nil => 1
    }

    println(listToString(list2))
    println(listMultiPly(List(2,3,4)))
  }

}
