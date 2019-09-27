package com.flink.scala.kafka

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
/**
  * kafka 如何实现exactly once<br/>
  * https://flink.sojb.cn/dev/connectors/kafka.html
  *
  * @author chenwu on 2019.9.27
  */
object Kafka2_0_1Test {

  def main(args: Array[String]): Unit = {
    val property = new Properties()
    property.setProperty("bootstrap.servers", "localhost:9092")
    property.setProperty("group.id", "test-flink")
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    /**
      * 启用Flink的检查点后，Flink Kafka Consumer将使用主题中的记录，并以一致的方式定期检查其所有Kafka偏移以及其他 算子操作的状态。
      * 如果作业失败，Flink会将流式程序恢复到最新检查点的状态，并从存储在检查点中的偏移量开始重新使用来自Kafka的记录。
      */
    env.enableCheckpointing(5000l)
    val kafka2_0_1Stream = env.addSource(new FlinkKafkaConsumer[String]("test-0",new SimpleStringSchema(),property))
    //kafka2_0_1Stream.assignAscendingTimestamps(_.g)
    val wordStream = kafka2_0_1Stream.flatMap(_.split("\\s+"))
    wordStream.print()
    env.execute("Kafka2_0_1Test")
  }
}
