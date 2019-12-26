package com.flink.scala2.connnector

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.createTypeInformation
import org.apache.flink.streaming.connectors.fs.{Clock, StreamWriterBase, Writer}
import org.apache.flink.streaming.connectors.fs.bucketing.{Bucketer, BucketingSink}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.hadoop.fs.{Path}

class MyBucketer extends Bucketer[(String, String)] {
  override def getBucketPath(clock: Clock, basePath: Path, element: (String, String)): Path = {
    val afterFix = element._2 + "-" + clock.currentTimeMillis()
    new Path(basePath + "/" + afterFix)
  }
}

class MyWriter extends StreamWriterBase[(String, String)] {

  override def write(element: (String, String)): Unit = {
    val content = "id:" + element._1 + ",value:" + element._2
    val fsDataOutputStream = this.getStream
    fsDataOutputStream.write(content.getBytes)
    fsDataOutputStream.write(10) //输出换行符
  }

  override def duplicate(): Writer[(String, String)] = {
    //这里一定要新创建一个writer,否则会报outputStream流被打开的错误
    new MyWriter()
  }
}

/**
  * flink 输出到hdfs<br/>
  * 可以参考https://blog.csdn.net/magic_kid_2010/article/details/96285735
  *
  * @author chenwu on 2019.12.26
  */
object BucketingSinkTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(1)
    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "127.0.0.1:9092")
    properties.setProperty("group.id", "BucketingSinkTest")
    val flinkKafkaConsumer = new FlinkKafkaConsumer[String]("test-210", new SimpleStringSchema(), properties)
    val kafkaStream = env.addSource(flinkKafkaConsumer)
    //输入aa,1  第一个aa表示内容,第二个表示值
    val mapStream = kafkaStream.map(item => (item.split(",")(0), item.split(",")(1)))
    //todo:为什么这里输出的都是这样的文件
    /**
      * 输出的文件都是这种带pending格式
      *
      * hadoop fs -ls hdfs://10.40.11.12:8020/user/chenwu/flink/11111-1577344877331
      * Found 1 items
      * -rw-r--r--   3 Administrator supergroup         21 2019-12-26 15:22 hdfs://10.40.11.12:8020/user/chenwu/flink/11111-1577344877331/_.log-0-0.pending
      */
    val hdfsSink = new BucketingSink[(String, String)]("hdfs://10.40.11.12:8020/user/chenwu/flink/")
    hdfsSink.setBucketer(new MyBucketer)
    hdfsSink.setWriter(new MyWriter)
    hdfsSink.setBatchSize(10) //这里设置成10B就滚动输出,默认为384M
    hdfsSink.setBatchRolloverInterval(5 * 1000) //滚动写入新文件的时间,这里设置成5秒
    hdfsSink.setPartPrefix(".log")
    mapStream.addSink(hdfsSink)
    env.execute("BucketingSinkTest")
  }


}
