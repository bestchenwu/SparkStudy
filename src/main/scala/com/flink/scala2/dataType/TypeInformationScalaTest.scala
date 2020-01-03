package com.flink.scala2.dataType

import com.flink.scala2.watermark.MyEvent
import org.apache.flink.api.scala.createTypeInformation

/**
  * scala的TypeInformation识别
  *
  * @author chenwu on 2020.1.3
  */
object TypeInformationScalaTest {

  def main(args: Array[String]): Unit = {
    val stringTypeInfo = createTypeInformation[MyEvent]
    println(stringTypeInfo)
  }
}
