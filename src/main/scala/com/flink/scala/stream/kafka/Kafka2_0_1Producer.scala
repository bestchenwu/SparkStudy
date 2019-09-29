package com.flink.scala.stream.kafka

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
import org.apache.flink.streaming.connectors.kafka.internals.KeyedSerializationSchemaWrapper

/**
  * 原文可以参考:https://flink.sojb.cn/dev/connectors/kafka.html<br/>
  * 也可以参考https://www.jianshu.com/p/9f45160d08e1<br/>
  * 将消息流写入到kafka
  *
  * @author chenwu on 2019.9.27
  */
object Kafka2_0_1Producer {

  def main(args: Array[String]): Unit = {
      val env = StreamExecutionEnvironment.createLocalEnvironment(1)
      val dataStream = env.readTextFile("D:\\brand.txt")
      val wordStream = dataStream.flatMap(_.split("\\s+"))
      val property = new Properties()
      property.setProperty("bootstrap.servers","localhost:9092")
      property.setProperty("group.id","test-flink")
      //val flinkKafkaProducer = new FlinkKafkaProducer[String]("test-0",new SimpleStringSchema(),property)
      //在kafka0.11版本及以上开启了exactly-once
      //需要注意的是当开启exactly-once的时候
      //必须给消费者加上参数isolation.level=read_committed，这是因为Kafka的事务支持是给写入的数据分为committed和uncomitted，
      // 如果使用默认配置的consumer，读取的时候依然会读取所有数据而不是根据事务隔离
      val flinkKafkaProducer = new FlinkKafkaProducer[String]("test-0",new KeyedSerializationSchemaWrapper(new SimpleStringSchema()),property,FlinkKafkaProducer.Semantic.EXACTLY_ONCE)
      wordStream.addSink(flinkKafkaProducer)
      env.execute("Kafka2_0_1Producer")
  }
}
