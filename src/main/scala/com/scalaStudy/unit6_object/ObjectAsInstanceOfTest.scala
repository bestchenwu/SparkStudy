package com.scalaStudy.unit6_object

object ObjectAsInstanceOfTest {

  def main(args: Array[String]): Unit = {
      val a:Int = 1
      //asInstanceOf表示是类型强制转换
      val b = a.asInstanceOf[Long]
      println(b.getClass)
  }
}
