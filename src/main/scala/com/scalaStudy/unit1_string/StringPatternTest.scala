package com.scalaStudy.unit1_string

/**
  * 字符串模式测试类
  *
  * @author chenwu on 2020.5.10
  */
object StringPatternTest {

  def main(args: Array[String]): Unit = {
      val pattern = "[0-9]+".r
      val str = "abc123def78gh12"
      val matches = pattern.findAllMatchIn(str)
      while(matches.hasNext){
          val matchItem = matches.next()
          println("match item:"+matchItem.source)
      }
  }
}
