package com.scalaStudy.unit9_FunctionPrograming

class FunctionTest {

  def add = (x: Int, y: Int) => x + y

  /**
    * 将函数作为变量传递到函数里面
    *
    * @param callBack
    */
  def executionFunction(callBack: () => Unit): Unit = {
    callBack()
  }

  def speakHello = () => println("hello")

}

object FunctionTest {

  def main(args: Array[String]): Unit = {
    val functionTest = new FunctionTest
    functionTest.executionFunction(functionTest.speakHello)
  }
}
