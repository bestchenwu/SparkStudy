package com.flink.scala2.table.tableStreaming.userDefined

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.{DataSet, ExecutionEnvironment => JavaExecutionEnvironment}
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala.typeutils.Types
import org.apache.flink.table.api.TableSchema
import org.apache.flink.table.api.scala.BatchTableEnvironment
import org.apache.flink.table.sources.{BatchTableSource, TableSource}
import org.apache.flink.types.Row
import org.apache.flink.api.scala._
import scala.collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer

/**
  * 自定义batchTableSource
  *
  * @author chenwu on 2020.5.28
  */
class MyBatchTableSource(val fieldNames: Array[String], val dataList: List[()]) extends BatchTableSource[Row] {

  var fieldTypeInformation:Array[TypeInformation[_]] = null

  def MyBatchTableSource(): Unit = {
    if (dataList.isEmpty) {
      throw new IllegalStateException("dataList can't be Empty")
    }
    val fieldTypes: ArrayBuffer[TypeInformation[_]] = ArrayBuffer[TypeInformation[_]]()
    for (item <- dataList) {
      val typeInformation: TypeInformation[_] = item.getClass.getSimpleName match {
        case "String" => Types.STRING
        case "Integer" => Types.INT
        case "Long" => Types.LONG
        case otherType => throw new IllegalArgumentException(s"unsupported type{$otherType}")
      }
      fieldTypes.append(typeInformation)
    }
    fieldTypeInformation = fieldTypes.toArray
  }

  override def getDataSet(execEnv: JavaExecutionEnvironment): DataSet[Row] = {
    val env = new ExecutionEnvironment(execEnv)
    val dataset = env.fromCollection(dataList)
    null
  }

  override def getReturnType: TypeInformation[Row] = {
      Types.ROW(fieldNames,fieldTypeInformation)
  }

  override def getTableSchema: TableSchema = {
      new TableSchema(fieldNames,fieldTypeInformation)
  }
}
