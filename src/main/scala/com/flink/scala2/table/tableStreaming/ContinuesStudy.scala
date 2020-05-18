package com.flink.scala2.table.tableStreaming

import java.sql.Timestamp
import java.util.Properties

import com.spark.constants.SymbolConstants
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.typeutils.TupleTypeInfo
import org.apache.flink.api.scala.typeutils.Types
import org.apache.flink.streaming.api.datastream.DataStream
import org.apache.flink.streaming.api.environment.{StreamExecutionEnvironment => JavaStreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.table.sources.{DefinedProctimeAttribute, StreamTableSource}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.sinks.CsvTableSink
//
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.api.{Table, TableSchema, Tumble}
import org.apache.flink.types.Row
import java.lang.{Integer => JInteger}

import scala.collection.mutable

/**
  * 订单的流式数据<br>
  * 数据格式如下所示:<br>
  *
  * SELECT * FROM Orders;<br>
  *
  * rowtime amount currency<br>
  * ======= ====== =========<br>
  * 10:15        2 Euro<br>
  * 10:30        1 US Dollar<br>
  * 10:32       50 Yen<br>
  * 10:52        3 Euro<br>
  * 11:04        5 US Dollar
  */
class OrderSource extends StreamTableSource[Row] with DefinedProctimeAttribute {

  private val names = Array[String]("rowTime", "o_amount", "o_currency")
  private val types = Array[TypeInformation[_]](Types.SQL_TIMESTAMP, Types.INT, Types.STRING)

  override def getDataStream(execEnv: JavaStreamExecutionEnvironment): DataStream[Row] = {
    val properties = new Properties()
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "orderSourceGroup")
    val orderKafkaSource = new FlinkKafkaConsumer[String]("test-order", new SimpleStringSchema(), properties)
    val scalaEnv = new StreamExecutionEnvironment(execEnv)
    val flinkKafkaStream = scalaEnv.addSource(orderKafkaSource)(Types.STRING)
    val outputStream = flinkKafkaStream.map(item => {
      val arrays = item.split(SymbolConstants.SYMBOL_DH)
      val currency = arrays(0)
      val amount = new JInteger(arrays(1))
      val currentTime = System.currentTimeMillis()
      val userActionTime = new Timestamp(currentTime)
      Row.of(userActionTime, amount, currency)
    })(Types.ROW(names, types))
    outputStream.javaStream
  }

  override def getProctimeAttribute: String = "rowTime"

  override def getReturnType: TypeInformation[Row] = {
    Types.ROW(names, types)
  }

  override def getTableSchema: TableSchema = {
    new TableSchema(names, types)
  }
}

/**
  * 汇率的流式数据
  *
  * SELECT * FROM RatesHistory;
  *
  * rowtime currency   rate
  * 09:00   US Dollar   102
  * 09:00   Euro        114
  * 09:00   Yen           1
  * 10:45   Euro        116
  * 11:15   Euro        119
  * 11:49   Pounds      108
  **/
class RatesHistorySource extends StreamTableSource[Row] with DefinedProctimeAttribute {

  private val names = Array[String]("rowTime", "currency", "rate")
  private val types = Array[TypeInformation[_]](Types.SQL_TIMESTAMP, Types.INT, Types.INT)

  override def getDataStream(execEnv: JavaStreamExecutionEnvironment): DataStream[Row] = {
    val properties = new Properties()
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ratesSourceGroup")
    val orderKafkaSource = new FlinkKafkaConsumer[String]("test-rates", new SimpleStringSchema(), properties)
    val scalaEnv = new StreamExecutionEnvironment(execEnv)
    val flinkKafkaStream = scalaEnv.addSource(orderKafkaSource)(Types.STRING)
    val outputStream = flinkKafkaStream.map(item => {
      val arrays = item.split(SymbolConstants.SYMBOL_DH)
      val currency = arrays(0)
      val rate = new JInteger(arrays(1))
      val currentTime = System.currentTimeMillis()
      val userActionTime = new Timestamp(currentTime)
      Row.of(userActionTime, currency, rate)
    })(Types.ROW(names, types))
    outputStream.javaStream
  }

  override def getProctimeAttribute: String = "rowTime"

  override def getReturnType: TypeInformation[Row] = {
    Types.ROW(names, types)
  }

  override def getTableSchema: TableSchema = {
    new TableSchema(names, types)
  }
}

/**
  * flink流的连续查询
  *
  * @author chenwu on 2020.5.18
  */

import org.apache.flink.table.api.scala._

object ContinuesStudy {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    val tableEnv = StreamTableEnvironment.create(env)
    //tableEnv.registerTableSource("Orders",new OrderSource)
    //val interval: String = "5"
    //val orderTable: Table = tableEnv.sqlQuery("SELECT " + " TUMBLE_START(rowTime, INTERVAL '" + interval + "' SECOND) as rowTime, " + "o_currency," + "SUM(o_amount) as o_amount " + " FROM  Orders" + " GROUP BY TUMBLE(rowTime, INTERVAL '" + interval + "' SECOND),o_currency").select('o_currency,'o_amount)
    //测试流式表的数据写入
    //    val tableNames = Array[String]("o_currency", "o_amount")
    //    val tableTypes = Array[TypeInformation[_]](Types.STRING, Types.INT)
    //    val myRetractStreamSink = new MyRetractStreamSink(tableNames, tableTypes, "D:\\logs\\flinkSink\\continuesStudy.csv", true)
    //    tableEnv.registerTableSink("continuesSink", tableNames, tableTypes, myRetractStreamSink)
    //    orderTable.insertInto("continuesSink")

    val orderData = new mutable.MutableList[(String, Long)]
    orderData.+=(("Euro", 2l))
    orderData.+=(("Euro", 114L))
    orderData.+=(("Yen", 1L))
    orderData.+=(("Euro", 116L))
    orderData.+=(("Euro", 119L))
    import org.apache.flink.api.scala._
    val orders = env
      .fromCollection(orderData)
      .toTable(tableEnv, 'o_currency, 'o_amount, 'o_proctime.proctime)
    tableEnv.registerTable("Orders", orders)

    val ratesHistoryData = new mutable.MutableList[(String, Long)]
    ratesHistoryData.+=(("US Dollar", 102L))
    ratesHistoryData.+=(("Euro", 114L))
    ratesHistoryData.+=(("Yen", 1L))
    ratesHistoryData.+=(("Euro", 116L))
    ratesHistoryData.+=(("Euro", 119L))
    import org.apache.flink.api.scala._
    val ratesHistory = env
      .fromCollection(ratesHistoryData)
      .toTable(tableEnv, 'r_currency, 'r_rate, 'r_proctime.proctime)
    tableEnv.registerTable("RatesHistory", ratesHistory)
    //tableEnv.registerTable("RatesHistory", ratesHistory)
    //    val ratesTable = ratesHistory.select('r_currency,'r_rate)
    //    tableEnv.registerTableSink("ratesSink",Array[String]("r_currency","r_rate"),Array[TypeInformation[_]](Types.STRING,Types.LONG),new CsvTableSink("D:\\logs\\flinkSink\\rates.csv","|"))
    //    ratesTable.insertInto("ratesSink")
    //todo:流失表连续查询不可用? 无法读到o_currency
    val rates = ratesHistory.createTemporalTableFunction('r_proctime, 'r_currency) // <==== (1)
    tableEnv.registerFunction("Rates", rates) // <==== (2)
    val resultTable = orders.joinLateral(rates('o_proctime), 'r_currency === 'o_currency)
      .select('o_currency, ('o_amount * 'r_rate).sum as 'totalAmount)
    tableEnv.registerTableSink("resultSink", Array[String]("o_currency", "totalAmount"), Array[TypeInformation[_]](Types.STRING, Types.LONG), new CsvTableSink("D:\\logs\\flinkSink\\rateJoin.csv", "|"))
    env.execute("ContinuesStudy")
  }
}
