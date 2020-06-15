package com.scalaFuntionProgramming_2times.unit11_Moniad

import com.scalaFuntionProgramming_2times.unit11_Moniad.Foldable.optionFoldable

object FoldableTest {

  def main(args: Array[String]): Unit = {
    val intOption = try {
      Some(10 / 2)
    } catch {
      case _: Throwable => None
    }
    val result = optionFoldable.foldLeft(intOption)(9)((x:Int,y:Int)=>x*y)
    println(result)
  }
}
