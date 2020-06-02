package com.scalaStudy.unit3_controll_flow

/**
  * 自定义控制结构
  *
  * @author chenwu on 2020.6.2
  */
object SelfDefinedControlTest {

  /**
    * 实现whileList(i<5){//doSomeThing}
    * testCondition: => Boolean 这里的testCondition表示一个返回类型为boolean的函数
    * codeBlock: => Unit表示返回类型为Unit的函数
    *
    * @param testCondition
    * @param codeBlock
    */
  def whileList(testCondition: => Boolean)(codeBlock: => Unit) {
    while (testCondition) {
      codeBlock
    }
  }

  def main(args: Array[String]): Unit = {
    var i = 1
    whileList(i < 5) {
      println(i * 3)
      i += 1
    }
  }
}
