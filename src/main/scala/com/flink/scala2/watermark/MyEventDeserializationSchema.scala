package com.flink.scala2.watermark

import org.apache.flink.api.common.serialization.DeserializationSchema
import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}

/**
  * 自定义kafka类型解析器
  *
  * @tparam T
  */
class MyEventDeserializationSchema[T] extends DeserializationSchema[MyEvent[T]] {
  override def deserialize(message: Array[Byte]): MyEvent[T] = {
    null
  }

  override def isEndOfStream(nextElement: MyEvent[T]): Boolean = {
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
