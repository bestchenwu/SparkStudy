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
      val matches = pattern.findAllIn(str)
      while(matches.hasNext){
          val matchString = matches.next()
          println(matchString)
      }
      //如果想从字符串中抽取匹配的模式
      val pattern2 = "([0-9]+)\\s+([a-zA-Z]+)".r
      val pattern2(number,name) = "100 bananas"
      println(s"number=$number,name=$name")
  }
}
