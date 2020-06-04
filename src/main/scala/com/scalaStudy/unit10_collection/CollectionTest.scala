package com.scalaStudy.unit10_collection

import scala.collection.mutable.ArrayBuffer

object CollectionTest {

  def main(args: Array[String]): Unit = {
    //ArrayBuffer是可变索引序列的首选，可变链表索引的首选是ListBuffer
    //    val arrayBuffer = ArrayBuffer("a", "b")
    //    arrayBuffer += "c"
    //    //输出abc
    //    println(arrayBuffer.foldRight("")((a, b) => a + b))
    //    arrayBuffer ++= List("d","e")
    //    //输出abcde
    //    println(arrayBuffer.foldRight("")((a, b) => a + b))
    //这条语句编译不通过的原因在于++=接受一个TravsableOnce[A]类型的参数,它只允许一个元素,而不是两个
    //arrayBuffer ++= Map("d"<-"e")

    //    val lol = List(List(1,2),List(2,3))
    ////    val flattenList = lol.flatten
    ////    println(flattenList)

    //    val someList = Vector(Some(1), None, Some(2), None)
    //    val someFlattenList = someList.flatten
    //    //输出1,2 这个例子把None排除掉
    //    println(someFlattenList)
    //
    //    val list = List("a", "1", "b", "2", "c")
    //    //这里的flatMap等效于map(toInt).flattern
    //    val result = list.flatMap(i => {
    //      try {
    //        Some(i.toInt)
    //      } catch {
    //        case _: NumberFormatException => None
    //      }
    //    }).sum
    //    println(result)

    //val list = List(1, 2, 3, 4, 5)
    //      val groupMap = list.groupBy(_>3)
    //      println(groupMap)
    //      val tupleList = list.partition(_<3)
    //      println(tupleList)
//    val tupleList = List((List("marry"),List("jack")),(List("sweet"),List("haha")))
//    //对于一个tuple2的序列，即该序列只有两个元素
//    //unzip每次取序列的一个元素，再从另外一个序列里取一个元素，组成一个新的序列
//    val (man, woman) = tupleList.unzip
//    println(man)
//    println(woman)
//      val list = List(1.0f,2.0f,3.0f)
//      def divide = (x:Float,y:Float)=>x/y
//      //从左往右依次迭代
//      val leftResult = list.reduceLeft(divide)
//      //从右往左依次迭代
//      val rightResult = list.reduceRight(divide)
//      //这里输出0.16  1/2=0.5 然后0.5/3 = 0.166
//      println(leftResult)
//      //输出1.5
//      println(rightResult)
        val list1 = List(1,2,3,4)
        val list2 = List(4,5,6,7)
        //将list1添加到list2的前面 形成一个新的List
        val list3 = list1:::list2
        println(list3)
  }
}
