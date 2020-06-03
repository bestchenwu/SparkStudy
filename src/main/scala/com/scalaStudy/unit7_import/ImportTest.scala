package com.scalaStudy.unit7_import

/** 在导入的时候隐藏Random类，但是util下的其他类还是可以使用 **/
import java.util
import java.util.{Random => _, _}

object ImportTest {

  def main(args: Array[String]): Unit = {
      //试着这样用，会报Random类找不到
      //val random =new Random()
      //但是如下使用java的ArrayList则可以
      val list = new util.ArrayList()
  }
}
