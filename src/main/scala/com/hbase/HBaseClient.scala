package com.hbase

import java.io.IOException

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{CellUtil, TableName}
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory, Get, Table}
import org.apache.hadoop.hbase.util.Bytes

import scala.collection.JavaConversions._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * hbaseClient hbase的客户端工具类
  *
  * @param hbaseFile
  */
class HBaseClient(val hbaseFile: String) extends Serializable {

  /**
    * 获取hbase链接
    *
    * @return
    * @author chenwu on 2019.12.17
    */
  private def getHTable(tableName: String): Table = {
    val conf = new Configuration()
    conf.addResource(hbaseFile)
    val connection = ConnectionFactory.createConnection(conf)
    val htable = connection.getTable(TableName.valueOf(tableName))
    htable
  }

  /**
    * 关闭table
    *
    * @param table
    * @author chenwu on 2019.12.17
    */
  private def closeHTable(table: Table): Unit = {
    try {
      table.close()
    } catch {
      case e: IOException => e.printStackTrace()
    }

  }

  /**
    * 批量查询hbase表
    *
    * @param rowKeys
    * @param tableName
    * @param familyName
    * @param columnName
    * @return rowkey和value的对应关系
    * @author chenwu on 2019.12.17
    */
  def queryTable(rowKeys: List[String], tableName: String, familyName: String, columnName: String): mutable.Map[String, String] = {
    val table = getHTable(tableName)
    val getArray = ArrayBuffer[Get]()
    rowKeys.foreach(rowkey => {
      val get = new Get(Bytes.toBytes(rowkey))
      get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName))
      getArray.append(get)
    })
    val results = table.get(getArray)
    val rowValueMap = mutable.HashMap[String, String]()
    results.foreach(result => {
      val cellList = result.getColumnCells(Bytes.toBytes(familyName), Bytes.toBytes(columnName))
      for (cell <- cellList) {
        val value = Bytes.toString(CellUtil.cloneValue(cell))
        val row = Bytes.toString(CellUtil.cloneRow(cell))
        rowValueMap.update(row, value)
      }
    })
    closeHTable(table)
    rowValueMap
  }
}
