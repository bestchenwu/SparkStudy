package com.flink.scala2.table.tableStreaming.userDefined.functions

import java.util.Properties

import com.spark.constants.SymbolConstants
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.table.api.scala._
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.flink.api.scala._
import org.apache.flink.types.Row

/**
  * [[WeightAvgSumFunction]]的测试类
  *
  * @author chenwu on 2020.6.1
  */
object UserDefinedAggregateFunctionTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    val tableEnv = StreamTableEnvironment.create(env)
    val props = new Properties()
    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test-flink")
    val flinkKafkaConsumer = new FlinkKafkaConsumer[String]("test-order", new SimpleStringSchema(), props)
    val kafkaStream = env.addSource(flinkKafkaConsumer)
    val dataStream = kafkaStream.map(item => {
      val stringArray = item.split(SymbolConstants.SYMBOL_DH)
      (stringArray(0),stringArray(1).toLong, stringArray(1).toInt)
    })
    tableEnv.registerDataStream("userPoints",dataStream,'userId,'points,'weight)
    //val table = tableEnv.fromDataStream(dataStream,'points,'weight)
    tableEnv.registerFunction("wAvg",new WeightAvgSumFunction())
    //todo:
    /**
    Actual: (java.lang.Long, java.lang.Integer)
    Expected: (com.flink.scala2.table.tableStreaming.userDefined.functions.WeightAvgSum, long, long)
      **/
    val table = tableEnv.sqlQuery("select userId,wAvg(points,weight) as wAvgNumber from userPoints group by userId")
    table.toDataSet[Row].print()
    //val queryResult = queryResult.select('wAvg('points,'weight))
  }
}
