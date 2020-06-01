package com.flink.scala2.dataType

import com.flink.scala2.watermark.MyEvent
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.typeutils.{PojoField, PojoTypeInfo}
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.api.scala.typeutils.Types

import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._
/**
  * scala的TypeInformation识别
  *
  * @author chenwu on 2020.1.3
  */
object TypeInformationScalaTest {

  def main(args: Array[String]): Unit = {
    //    val stringTypeInfo = createTypeInformation[MyEvent]
    //    println(stringTypeInfo)
    //这种方式和上面的方式是一样的，获得是GenericTypeInfo
    //      val myEventType = TypeInformation.of(MyEvent.getClass)
    //      println(myEventType)
    //创建PojoTypeInfomation
    val myEventClazz:Class[MyEvent] = classOf[MyEvent]
    val pojoFieldList: ListBuffer[PojoField] = ListBuffer()
    for (field <- myEventClazz.getDeclaredFields) {
      val fieldType = field.getType
      val fieldTypeInfomation = TypeInformation.of(fieldType)
      pojoFieldList.append(new PojoField(field, fieldTypeInfomation))
    }
    val pojoTypeInfo = new PojoTypeInfo[MyEvent](myEventClazz,pojoFieldList.toList)
    println(pojoTypeInfo)
    //另外一种创建PojoTypeInfomation
    val fieldMap : Map[String, TypeInformation[_]] = Map("time"->Types.LONG,"data"->Types.STRING)
    val anotherPojoTypeInfo = Types.POJO(classOf[MyEvent],fieldMap)
    println(anotherPojoTypeInfo)
  }
}
