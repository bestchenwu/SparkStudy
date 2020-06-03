package com.scalaStudy

/**
  * 包对象 对象名称和所在包名相同，同时将package定义里的最后包名删除
  * 声明的时候用package object的方式
  *
  * @author chenwu on 2020.6.3
  */
package object unit6_object {

  val MAGIC_NUM = 12

  def speak(a: Any) = println(a)
}
