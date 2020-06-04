package com.scalaStudy.unit11_List_Map_Set

object ArrayTest {

  def main(args: Array[String]): Unit = {
    //定义多维数组
    //定义两行三列数组
    val array = Array.ofDim[Int](2, 3)
    array(0)(0) = 1
    array(0)(1) = 2
    array(0)(2) = 3
    array(1)(0) = 4
    array(1)(1) = 5
    array(1)(2) = 6
    for {
      i <- 0 to 1
      j <- 0 to 2
    } println(array(i)(j))
    //或者使用嵌套数组
    val array2 = Array(Array(1,2,3),Array(4,5,6))
    for {
      i <- 0 to 1
      j <- 0 to 2
    } println(array2(i)(j))
  }
}
