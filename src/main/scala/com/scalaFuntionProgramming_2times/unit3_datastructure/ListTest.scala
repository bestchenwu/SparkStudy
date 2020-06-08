package com.scalaFuntionProgramming_2times.unit3_datastructure

object ListTest {

  def main(args: Array[String]): Unit = {
    //    val list = Crons(2, Crons(3, Crons(4, Nil)))
    //    var result = List.sum(list)
    //    println(result)
    //    result = List.product(list)
    //    println(result)
    //
    //    val x = List(1, 2, 3, 4, 5) match {
    //      case Crons(x, Crons(2, Crons(4, _))) => x
    //      case Nil => 42
    //      case Crons(x, Crons(y, Crons(3, Crons(4, _)))) => x + y
    //      case Crons(h, t) => h + List.sum(t)
    //      case _ => 101
    //    }
    //    assert(x == 3)
    //
    //    val tailList = List.tail(list)
    //    println(tailList)
    //
    //    val newList = List.setHead(list, 10)
    //    println(newList)
    //
    //    var dropList = List.drop(newList, 2)
    //    println(dropList)
    //
    //    dropList = List.drop(Nil, 10)
    //    println(dropList)
    //
    //    dropList = List.drop(newList, 0)
    //    println(dropList)
    //
    //    val dropWhileList = List.dropWhile(newList, (x: Int) => x % 2 == 0)
    //    println(dropWhileList)
    //
    // val list2 = List.apply(22, 55, 14)
    //    val appendList = List.append(newList, list2)
    //    println(appendList)
    //
    //    val list3 = List.init(list2)
    //    println(list3)
    //
    //val list4 = List.apply(24, 6, 14)
    //    val list5 = List.dropWhile2(list4)(_ % 2 == 0)
    //    println(list5)

    //    val foldRightResult = List.foldRight(list4, 7.0f)((x: Int, y: Float) => x / y)
    //    assert(foldRightResult == 8.0f)
    //
    //    val result = List.sum2(list4)
    //    println(result)

    //    val length = List.length(list4)
    //    assert(length == 3)
    //
    //    val foldLeftResult = List.foldLeft(list4, 8)((x: Int, y: Int) => x / y)
    //    assert(foldLeftResult == 7)

    //    val reverseList = List.reverseList2(list4)
    //    println(reverseList)

    val list1 = List(1, 5, 8)
    val list2 = List(2, 9, 8, 7, 10)
//    val list = List.appendByFold(list1, list2)
//    println(list)
//
//    val list3 = List.add1ByFold(list2)
//    println(list3)
//
//    val listString = List.map(list2)((x: Int) => "a" + x)
//    println(listString)
//
//    val oddList = List.filter(list2)(_ % 2 == 1)
//    println(oddList)
//
//    val stringList = List("ab", "cd", "ef")
//    val resultList = List.flatMap(stringList)(str => List.apply(str: _*))
//    println(resultList)

    val addList = List.addList(list1,list2)
    println(addList)
  }
}
