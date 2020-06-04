package com.scalaStudy.unit11_List_Map_Set

import scala.collection.mutable.ListBuffer

object ListTest {

  def main(args: Array[String]): Unit = {
    //    val list = List(2)
    //    val list1 = 0 :: list
    //    //输出0 2
    //    println(list1)
    //    val list2 = list1 :+ (3)
    //    //输出0 2 3
    //    println(list2)
    //    val list3 = ListBuffer(1, 2, 3)
    //    list3 -= 2
    //    println(list3)
    val list4 = List("cherry", "banana", "origin")
    val sortedList = list4.sorted
    println(sortedList)
    //或者使用Scala工具类 但是只能对Array排序
    val array = list4.toArray
    scala.util.Sorting.quickSort(array)
    println(array)
  }
}
