package com.scalaStudy.unit1_string

/**
  * 自定义string的方法
  */

object MyImplictlyTest {


  def main(args: Array[String]): Unit = {
    //当前作用域的隐式对象
    //    implicit class IncrementWrapper(val s: String) {
    ////
    ////      def increment() = s.map(c => (c + 1).toChar)
    ////    }
    //或者使用类中的隐式转换
    //import StringUtils._
    //val s = "abc"
    //println(s.increment)
    //或者定义隐式方法
    implicit def transfer(s: String) = s.foldRight(0)((a, b) => a + b)

    val s = "a"
    println(3*s)
  }
}
