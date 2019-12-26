package com.flink.scala2.watermark

import org.apache.flink.api.common.serialization.DeserializationSchema
import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}

/**
  * 自定义kafka类型解析器
  *
  * @tparam T
  */
class MyEventDeserializationSchema extends DeserializationSchema[MyEvent] {
  override def deserialize(message: Array[Byte]): MyEvent = {
    val charArray = message.map(item => item.toChar)
    val str = charArray.mkString("")
    val time = str.split(",")(0).toLong
    val data = str.split(",")(1)
    MyEvent(time, data)
  }

  override def isEndOfStream(nextElement: MyEvent): Boolean = {
    false
  }

  override def getProducedType: TypeInformation[MyEvent] = {
    TypeInformation.of(new TypeHint[MyEvent] {
      override def getTypeInfo: TypeInformation[MyEvent] = {
        super.getTypeInfo
      }
    })
  }
}
