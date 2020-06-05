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

  def main(args: Array[String]): Unit = {
    //val result = calculate(5)
    //val result = calculate1(5)
    val result = calculate2(5)
    println(result)
  }
}
