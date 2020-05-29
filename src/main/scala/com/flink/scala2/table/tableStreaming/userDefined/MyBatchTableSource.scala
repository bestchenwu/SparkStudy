package com.flink.scala2.table.tableStreaming.userDefined

import com.spark.constants.SymbolConstants
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java
import org.apache.flink.api.java.typeutils.RowTypeInfo
import org.apache.flink.api.java.{DataSet, ExecutionEnvironment => JavaExecutionEnvironment}
import org.apache.flink.api.scala.typeutils.Types
import org.apache.flink.table.api.TableSchema
import org.apache.flink.types.Row
import org.apache.flink.table.sources.BatchTableSource

import scala.collection.JavaConversions._
import scala.collection.mutable.{ListBuffer}

/**
  * 自定义batchTableSource
  *
  * @author chenwu on 2020.5.28
  */
class MyBatchTableSource(val fieldNames: Array[String], val fieldTypes: Array[TypeInformation[_]], val dataList: ListBuffer[String]) extends BatchTableSource[Row] {

  private val returnType: RowTypeInfo = new java.typeutils.RowTypeInfo(fieldTypes, fieldNames)

  override def getDataSet(execEnv: JavaExecutionEnvironment): DataSet[Row] = {
    val rowList = dataList.map(item => {
      val spliteArray = item.split(SymbolConstants.SYMBOL_XHX)
      //注意这里row的维度，要声明为和returnType的维度一致
      val row = new Row(spliteArray.size)
      for ((item, index) <- spliteArray.zipWithIndex) {
        val fieldType = fieldTypes(index)
        val transferObject = fieldType match {
          case Types.INT => item.toInt
          case Types.LONG => item.toLong
          case _ => item
        }
        row.setField(index, transferObject)
      }
      row
    })
    execEnv.fromCollection(rowList, returnType)
  }

  override def getReturnType: TypeInformation[Row] = {
    Types.ROW(fieldNames, fieldTypes)
  }

  override def getTableSchema: TableSchema = {
    new TableSchema(fieldNames, fieldTypes)
  }
}
