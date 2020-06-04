package com.scalaStudy.unit11_List_Map_Set

object MapTest {

  def main(args: Array[String]): Unit = {
      //给map设置一个默认值
      val map = Map("abc"->"def").withDefaultValue("not found")
      val str = map("ddd")
      println(str)
  }
}
