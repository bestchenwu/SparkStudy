package com.flink.scala.stream.window

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * flink window的测试类<br/>
  * window用来对一个无限的流设置一个有限的集合,在有限的数据集上进行操作的一种机制<br/>
  * window又可以分为按time或者count的window,前者叫time-based,后者叫count-based<br/>
  * 关于窗口的描述 可见https://blog.csdn.net/zg_hover/article/details/87592060<br/>
  * 时间窗口又分为翻滚的时间窗口和滑动的时间窗口 比如设定一个1分钟的时间窗口<br/>
  * 翻滚的时间窗口将只计算当前1分钟内的数据，而不会管前1分钟或后1分钟的数据<br/>
  * 滑动窗口需要定义窗口的大小和每次在窗口的滑动的大小,理论上滑动的大小不能大于窗口的大小
  *
  * @author chenwu on 2019.9.4
  */
object FlinkWindowTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //设置处理时间为标准
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)
    val property = new Properties()
    property.setProperty("bootstrap.servers", "localhost:9092")
    property.setProperty("group.id", "test-flink")
    //todo:使用kafka2.0.1
//    val dataStream = env.addSource(new FlinkKafkaConsumer010[String]("test-010", new SimpleStringSchema(), property))
//    //基于时间  如果事先按照keyBy操作,那么使用timewindow,否则使用timeWindowAll
//    val stream = dataStream.keyBy(0).timeWindow(Time.hours(1l))
//    //比如这种方式就是定义窗口为1分钟,同时每次滑动30秒
//    //val stream = dataStream.keyBy(0).timeWindow(Time.minutes(1l),Time.seconds(30l));
//    //基于数量
//    //val stream = dataStream.keyBy(0).countWindow(11l)
//    stream.reduce(_ + _).addSink(println(_))
  }

}
