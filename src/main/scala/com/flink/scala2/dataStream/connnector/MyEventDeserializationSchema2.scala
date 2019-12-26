package com.flink.scala2.connnector

import com.flink.scala2.watermark.MyEvent
import org.apache.flink.api.common.serialization.DeserializationSchema
import org.apache.flink.api.common.typeinfo.TypeInformation

/**
  * 模仿{@link MyEventDeserializationSchema}写的kafka内容类型解析
  *
  * @author chenwu on 2019.12.18
  */
class MyEventDeserializationSchema2 extends DeserializationSchema[MyEvent] {
  override def deserialize(message: Array[Byte]): MyEvent = {
    val messageString = new String(message)
    //第一个为data,第二个为time
    val spliteArray = messageString.split(",")
    if (spliteArray.length < 2) {
      MyEvent(-1L, "")
    } else {
      val data = spliteArray(0)
      val time = spliteArray(1).toLong
      MyEvent(time, data)
    }
  }

  override def isEndOfStream(nextElement: MyEvent): Boolean = {
    false
  }

  override def getProducedType: TypeInformation[MyEvent] = {
    null
  }
}
