package com.flink.scala2.table.tableStreaming

import com.flink.scala2.table.tableStreaming.userDefined.MyBatchTableSource
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala.typeutils.Types
import org.apache.flink.table.api.scala.BatchTableEnvironment
import org.apache.flink.table.api.scala._
import org.apache.flink.types.Row
import org.apache.flink.api.scala._

import scala.collection.mutable.ListBuffer
/**
  * [[com.flink.scala2.table.tableStreaming.userDefined.MyBatchTableSource]]的测试类
  *
  * @author chenwu on 2020.5.28
  */
object MyBatchTableSourceTest {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.createLocalEnvironment(2)
    val tableEnv = BatchTableEnvironment.create(env)
    val fieldNames = Array("name", "data")
    val fieldTypes = Array[TypeInformation[_]](Types.STRING, Types.INT)
    val dataList = ListBuffer("sweet_1", "jack_2", "sweet_3")
    tableEnv.registerTableSource("my-source", new MyBatchTableSource(fieldNames, fieldTypes, dataList))
    val queryResult = tableEnv.scan("my-source").groupBy('name).select('name,'data.sum)
    queryResult.toDataSet[Row].print()
  }
}
