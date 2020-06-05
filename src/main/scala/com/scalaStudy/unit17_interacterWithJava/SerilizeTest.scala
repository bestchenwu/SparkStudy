package com.scalaStudy.unit17_interacterWithJava

import scala.annotation.varargs
import scala.beans.BeanProperty

/**
  * 使用这个标记就可以创建可以序列化的类
  *
  * @param price
  */
@SerialVersionUID(100l)
class Stock(@BeanProperty var price: Double, @BeanProperty var code: String) extends Serializable {
  override def toString: String = s"Stock[code=$code,price=$price]"
}

class Printer {

  /**
    * 使用varargs注解后,java代码就可以调用这个可变长参数列表的方法
    *
    * @param args
    */
  @varargs
  def printAllArgs(args: String*): Unit = {
    args.foreach(println)
  }
}


