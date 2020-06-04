package com.scalaStudy.unit9_FunctionPrograming

import com.scalaStudy.unit9_FunctionPrograming_closure.Foo

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
    //    val functionTest = new FunctionTest
    //    functionTest.executionFunction(functionTest.speakHello)

    //    var hello = "hello"
    //
    //    def speakHello(name: String) = println(s"$hello,$name")
    //
    //    val foo = new Foo
    //    foo.exec(speakHello, "raoshanshan")
    //    hello = "hela"
    //    foo.exec(speakHello, "lisisi")
    def sum(x: Int, y: Int, z: Int): Int = {
      x + y + z
    }

    //创建一个部分应用函数，接受一个int型的参数，返回一个int的值
    def f = sum(1, 2, _: Int)

    /**
      * 创建部分应用函数，部分应用函数表示只处理它认为是的对的值(通过isDefinedAt函数来控制)
      */
    val divide = new PartialFunction[Int, Int] {
      override def isDefinedAt(x: Int): Boolean = x != 0

      override def apply(x: Int): Int = 42 / x
    }

    def canDivide = (x: Int) => {
      if (divide.isDefinedAt(x)) {
        divide.apply(x)
      } else {
        Int.MinValue
      }
    }

    var result = canDivide(0)
    println(result)
    result = canDivide(6)
    println(result)

    // println(f(5))

  }
}
