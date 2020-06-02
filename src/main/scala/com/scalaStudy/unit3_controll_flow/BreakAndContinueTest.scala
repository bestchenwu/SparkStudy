package com.scalaStudy.unit3_controll_flow

import scala.util.control.Breaks.{break, breakable}

object BreakAndContinueTest extends App {

  //以下例子是模拟的break
  //实际上调用的是breakable这个方法
  breakable(
    for (i <- 0 to 10) {
      if (i == 3) {
        //抛出breakException
        break()
      } else {
        println(s"i=$i")
      }
    }
  )

  //以下例子是模仿的continue
  for (i <- 0 to 10) {
    breakable(
      if (i % 2 == 0) {
        break()
      } else {
        println(s"i=$i")
      }
    )
  }
}
