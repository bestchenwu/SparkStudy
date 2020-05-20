package com.flink.scala2.table.tableStreaming

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.scala.typeutils.Types
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.descriptors.{Json, Kafka, Schema}

/**
  * Table连接kafka source的例子
  *
  * @author chenwu on 2020.5.20
  */
object TableKafkaSourceTest {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    val tableEnv = StreamTableEnvironment.create(env)
    tableEnv.connect(
      new Kafka().
        version("universal").
        topic("test-order").
        property("zookeeper.connect", "localhost:2181")
        .property("bootstrap.servers", "localhost:9092")
        .property("group.id", "test-flink-kafka-table-soruce")
    )
      .withFormat(new Json()
        .jsonSchema("{" +
          "  type: 'object'," +
          "  properties: {" +
          "    name: {" +
          "      type: 'string'" +
          "    }," +
          "    data: {" +
          "      type: 'integer'" +
          "    }" +
          "  }" +
          "}"))
      .withSchema(
        new Schema().field("name", Types.STRING)
          .field("data", Types.JAVA_BIG_DEC)
      ).inAppendMode().registerTableSource("kafkaSource")
    val table = tableEnv.sqlQuery("select name,sum(data) as dataSum from kafkaSource group by name")
    val fieldNames = Array[String]("name", "dataSum")
    val fieldTypes = Array[TypeInformation[_]](Types.STRING, Types.JAVA_BIG_DEC)
    val myRetractStreamSink = new MyRetractStreamSink(fieldNames, fieldTypes, "D:\\logs\\flinkSink\\kafkaTableSink.csv", false)
    tableEnv.registerTableSink("kafkaTableSink", myRetractStreamSink)
    table.insertInto("kafkaTableSink")
    env.execute("TableKafkaSourceTest")
  }
}
