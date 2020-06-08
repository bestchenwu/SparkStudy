package com.scalaFuntionProgramming_2times.unit2_firstExample

/**
  * 练习2_3 2_4 2_5
  *
  * @author chenwu on 2020.6.8
  */
object Practice_2_3_2_4_2_5 {

  def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => ((b: B) => f(a, b))

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a: A, b: B) => f(a)(b)

  def compose[A, B, C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))

  def main(args: Array[String]): Unit = {
    val add = (x: Int, y: Int) => x + y

    val curry0 = curry[Int, Int, Int](add)
    println(curry0(1)(2))

    val product = (x: Int) => ((y: Int) => x * y)
    val uncurry0 = uncurry[Int, Int, Int](product)
    println(uncurry0(3, 5))

    val compose0 = compose((x:Int)=>x/2,(x:Int)=>x*3)
    println(compose0(8))
  }
}
