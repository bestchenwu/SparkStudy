package com.flink.scala.stream.watermark

import java.util.Properties

import com.flink.scala.stream.KafkaEvent
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.kafka.clients.consumer.{Consumer, ConsumerConfig, ConsumerRecord, KafkaConsumer}
import java.util.{Arrays => JavaArrays}

import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

import scala.collection.convert.WrapAsScala._

/**
  * kafka watermark的测试类(使用kafka的原始时间戳)
  *
  * @author chenwu on 2019.9.7
  */
object KafkaWatermarkTest {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val property = new Properties()
    property.setProperty("bootstrap.servers", "localhost:9092")
    property.setProperty("group.id", "test-flink")
    property.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer")
    property.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer")
    val sourceStream = env.addSource(new SourceFunction[KafkaEvent] {
      override def run(ctx: SourceFunction.SourceContext[KafkaEvent]): Unit = {
        val kafkaConsumer = new KafkaConsumer[String, String](property)
        kafkaConsumer.subscribe(JavaArrays.asList("test-0"))
        //val kafkaConsumer = new FlinkKafkaConsumer010[String]("test-010",new SimpleStringSchema(),property)
        var index = 0;
        try {
          while (true) {
            val consumerRecords = kafkaConsumer.poll(100l)
            val iterable = iterableAsScalaIterable[ConsumerRecord[String, String]](consumerRecords.records("test-0"))
            iterable.foreach(record => {
              val kafkaTimeStamp = record.timestamp()
              println(s"record=${record.value()},kafkaTimeStamp=${kafkaTimeStamp}")
              val array = record.value().split(",")
              val kafkaEvent = KafkaEvent(array(0), array(1).toLong, kafkaTimeStamp)
              ctx.collectWithTimestamp(kafkaEvent, kafkaEvent.timestamp)
              index+=1
              if(index%3==0){
                //这里是手工指定watermark,因为要用到kafka的原始timestamp
                val watermark = new Watermark(kafkaEvent.timestamp)
                println(s"watermark=${watermark.getTimestamp}")
                ctx.emitWatermark(watermark)
              }
            })
            kafkaConsumer.commitAsync()
          }
        } catch {
          case e:Exception => e.printStackTrace()
        } finally {
          try {
            kafkaConsumer.commitAsync()
          } finally {
            kafkaConsumer.close()
          }

        }
      }

      override def cancel(): Unit = {}
    })
    //val dataStream = env.addSource(new FlinkKafkaConsumer010[String]("test-010",new SimpleStringSchema(),property))
    val kafkaStream = sourceStream.keyBy(_.id).timeWindow(Time.seconds(5l))
    val windowStream = kafkaStream.apply(new WindowFunction[KafkaEvent,(String, Int, String, String, String, String), String, TimeWindow] {
      override def apply(key: String, window: TimeWindow, input: Iterable[KafkaEvent], out: Collector[(String, Int, String, String, String, String)]): Unit = {
        val list = input.toList.sortBy(_.timestamp)
        val sum = input.toList.map(_.value).sum
        println(s"sum=${sum}")
        out.collect(key, input.size, list.head.timestamp.toString, list.last.timestamp.toString, window.getStart.toString, window.getEnd.toString)
      }
    })
    windowStream.print()
    env.execute("KafkaWatermarkTest")
  }
}
