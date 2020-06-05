package com.scalaFuntionProgramming_2times.unit2_firstExample

import scala.annotation.tailrec

object Practice_2_1 {

  //原生写法
  def calculate(n: Int): Int = {

    def go(n: Int) = {
      if (n == 0) {
        0
      } else if (n == 1) {
        1
      } else {
        var k = 2
        var i = 0
        var j = 1
        var tmp = 0
        while (k <= n) {
          tmp = i + j
          i = j
          j = tmp
          k += 1
        }
        tmp
      }
    }

    go(n)
  }

  //递归写法
  def calculate1(n: Int): Int = {
    n match {
      case 0 => 0
      case 1 => 1
      case m => calculate1(m - 1) + calculate1(m - 2)
    }
  }

  //使用尾递归
  def calculate2(n: Int): Int = {

    @tailrec
    def go(n: Int, prev: Int, cur: Int): Int = n match {
      case 0 => prev
      case n => go(n - 1, cur, cur + prev)
    }

    go(n, 0, 1)

  }

  /**
    * 求n的阶乘
    *
    * @param n
    * @return
    * @author chenwu on 2020.6.5
    */
  def calculate_product(n: Int): Int = {

    @tailrec
    def go(n: Int, acc: Int): Int = {
      if (n <= 0) acc
      else go(n - 1, n * acc)
    }

    go(n, 1)
  }

  /**
    * 在数组里寻找第一个符合p的函数
    *
    * @param str
    * @param p
    * @tparam A
    * @return
    */
  def findFirst[A](str: Array[A], p: A => Boolean): Int = {

    @tailrec
    def loop(n: Int): Int = {
      if (n >= str.length) -1
      else if (p(str(n))) n
      else loop(n + 1)
    }

    loop(0)
  }

  def isSorted[A](array: Array[A], p: (A, A) => Boolean): Boolean = {

    if (array.isEmpty) {
      true
    }

    @tailrec
    def loop(n0: Int, n1: Int, result: Boolean): Boolean = {
      if (result == false) result
      else if (n1 >= array.length) result
      else loop(n1, n1 + 1, result && p(array(n0), array(n1)))
    }

    loop(0, 1, true)
  }

  def partial[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

  def main(args: Array[String]): Unit = {
    //val result = calculate(5)
    //val result = calculate1(5)
    //    val result = calculate2(5)
    //    println(result)
    val array = Array(1, 6, 4, 5, 8, 9)
    //val result = findFirst(array, (x: Int) => x % 2 == 0)
    val result = isSorted(array, (x: Int, y: Int) => x < y)
    println(result)
    val add = (x:Int,y:Int)=>x+y
    val myPartial = partial(3,add)
    println(myPartial(4))
  }
}
