package com.flink.scala2.connnector

import com.flink.scala2.watermark.MyEvent
import org.apache.flink.api.common.serialization.DeserializationSchema
import org.apache.flink.api.common.typeinfo.TypeInformation
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.{CharacterCodingException, Charset, CharsetDecoder}
import java.nio.CharBuffer

/**
  * 解析消息流  111,2019911211类似这样<br/>
  * ByteBuffer的使用可以参考https://blog.csdn.net/xialong_927/article/details/81044759
  *
  * @author chenwu on 2019.12.5
  */
class MyEventDeserializationSchema extends DeserializationSchema[MyEvent] {
  override def deserialize(message: Array[Byte]): MyEvent = {
    val byteBuffer = ByteBuffer.wrap(message).order(ByteOrder.LITTLE_ENDIAN)
    val stringMessage = bufferToString(byteBuffer)
    //val charArray = message.map(_.toChar)
    //val stringMessage = new String(charArray)
    val timeStamp = stringMessage.split(",")(0).toLong
    val data = stringMessage.split(",")(1)
    MyEvent(timeStamp, data)
  }

  override def isEndOfStream(nextElement: MyEvent): Boolean = {
    false
  }

  override def getProducedType: TypeInformation[MyEvent] = {
    null
  }

  /**
    * 将ByteBuffer转换为String
    *
    * @param byteBuffer
    * @return String
    * @author chenwu on 2019.12.5
    */
  private def bufferToString(byteBuffer: ByteBuffer): String = {
    var charSet: Charset = null
    var decoder: CharsetDecoder = null
    var charBuffer: CharBuffer = null
    try {
      charSet = Charset.forName("utf-8")
      decoder = charSet.newDecoder()
      charBuffer = decoder.decode(byteBuffer)
      charBuffer.toString
    } catch {
      case e: CharacterCodingException => throw new RuntimeException(e)
    }
  }
}
