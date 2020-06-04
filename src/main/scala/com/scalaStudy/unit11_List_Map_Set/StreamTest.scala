package com.scalaStudy.unit11_List_Map_Set

object StreamTest {

  def main(args: Array[String]): Unit = {
      //Stream是ListBuffer的另外一种形式
      val stream = 1 #:: 2 #::3 #:: Stream.Empty
      val list = stream.map(_*2)
      //输出Stream(2,?)
      println(list)
  }
}
