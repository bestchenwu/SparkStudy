package com.flink.scala.source

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.core.fs.FileSystem.WriteMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 测试kafka source连接
  *
  * @author chenwu on 2019.8.24
  */
object KafkaSource {

  def main(args: Array[String]): Unit = {
    val property = new Properties()
    property.setProperty("bootstrap.servers", "localhost:9092")
    property.setProperty("group.id", "test-flink")
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //切换成kafka 2..0.1版本
//    val stream = env.addSource(new FlinkKafkaConsumer010[String]("test-0", new SimpleStringSchema(), property))
//    val count = stream.flatMap(_.split("\\W+")).map(word => (word, 1)).keyBy(0).timeWindow(Time.seconds(5)).sum(1)
//    count.writeAsText("/data/problem/testFlink.txt",WriteMode.NO_OVERWRITE)
    env.execute("KafkaSource")
  }

}
