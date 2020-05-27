package com.flink.scala2.table.tableStreaming

import com.flink.scala2.table.tableStreaming.userDefined.MyFormatDescriptor
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala.typeutils.Types
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.descriptors.{Kafka, Schema}

/**
  * 自定义table format
  *
  * @author chenwu on 2020.5.26
  */
object TableKafkaSourceMyFormatTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    val tableEnv = StreamTableEnvironment.create(env)
    val descriptor = tableEnv.connect(new Kafka().version("universal").
      topic("test-order").
      property("zookeeper.connect", "localhost:2181")
      .property("bootstrap.servers", "localhost:9092")
      .property("group.id", "test-flink-kafka-table-soruce")
    ).withFormat(new MyFormatDescriptor().mySchema("name:string,data:int"))
      .withSchema(new Schema().field("name", Types.STRING).field("data", Types.INT))
    descriptor.inAppendMode().registerTableSource("MyFormatTable")
    val table = tableEnv.sqlQuery("select name,sum(data) as dataSum from MyFormatTable group by name")
    val fieldNames = Array[String]("name", "dataSum")
    val fieldTypes = Array[TypeInformation[_]](Types.STRING, Types.INT)
    val myRetractStreamSink = new MyRetractStreamSink(fieldNames, fieldTypes, "D:\\logs\\flinkSink\\MyFormatSink.csv", false)
    tableEnv.registerTableSink("kafkaTableSink", myRetractStreamSink)
    table.insertInto("kafkaTableSink")
    env.execute("TableKafkaSourceTest")
  }
}
