package com.flink.scala.stream.process

import com.spark.common.util.CommonDateUtil
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.{KeyedProcessFunction, ProcessFunction}
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector

import scala.util.control.Breaks.{break, breakable}

/**
  * (原文)https://flink.sojb.cn/dev/stream/operators/process_function.html
  *
  * 低级处理函数
  * @author chenwu on 2019.9.22
  */
object ProcessFunctionTest {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.createLocalEnvironment(2)
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)
    val dataStream1:DataStream[(String, String)] = env.addSource(new SourceFunction[(String, String)]() {

      var running: Boolean = true

      override def run(ctx: SourceFunction.SourceContext[(String, String)]): Unit = {
        val elements = List[(String, String)](("a", "1"), ("a", "2"), ("a", "3"), ("a", "4"), ("a", "5"), ("a", "6"))
        breakable {
          for (tuple <- elements) {
            if (!running) {
              break()
            }
            ctx.collect(tuple)
            //模拟窗口时间
            Thread.sleep(1000)
          }
        }

      }

      override def cancel(): Unit = {
        running = false
      }
    }).name("source1")


    val result = dataStream1.keyBy(0).process(new KeyByProcessFunction())
    result.print()
    env.execute("ProcessFunctionTest")
  }


  /**
    * The data type stored in the state
    */
  case class CountWithTimeStamp(key:String,count:Long,lastModified:Long)
  /**
    * The implementation of the ProcessFunction that maintains the count and timeouts
    * 这两个声明都不能放在main函数里,否则会报forward reference extends over definition of value result的错误
    */
  class KeyByProcessFunction extends ProcessFunction[(String,String),(String,Long)]{

    lazy val state:ValueState[CountWithTimeStamp] = getRuntimeContext.getState(new ValueStateDescriptor[CountWithTimeStamp]("myState",classOf[CountWithTimeStamp]))

    override def processElement(value: (String, String), ctx: ProcessFunction[(String, String), (String, Long)]#Context, out: Collector[(String, Long)]): Unit = {
      var currentTimeStamp:CountWithTimeStamp = null
      if(state==null){
        currentTimeStamp = CountWithTimeStamp(value._1,1l,ctx.timestamp())
      }else{
        val lastModifiedState = state.value()
        currentTimeStamp = lastModifiedState match {
          case null=>CountWithTimeStamp(value._1,1l,CommonDateUtil.getCurrentTimeStamp)
          case CountWithTimeStamp(key,count,lastModified)=>CountWithTimeStamp(value._1,count+1l,CommonDateUtil.getCurrentTimeStamp)
        }
      }

//      val currentTimeStamp = state match {
//        case null=>CountWithTimeStamp(value._1,1l,ctx.timestamp())
//        case lastState:ValueState[CountWithTimeStamp] =>{
//          val lastCountWithTimeStamp = lastState.value()
//          CountWithTimeStamp(value._1,lastCountWithTimeStamp.count+1l,ctx.timestamp())
//        }
//      }
      state.update(currentTimeStamp)
      ctx.timerService().registerEventTimeTimer(currentTimeStamp.lastModified+1000l)
    }

    //todo:这里的onTimer什么时候触发
    override def onTimer(timestamp: Long, ctx: ProcessFunction[(String, String), (String, Long)]#OnTimerContext, out: Collector[(String, Long)]): Unit = {
      state.value() match{
        case CountWithTimeStamp(key,count,lastModified)=>{
          if(lastModified+6000l==timestamp){
            out.collect((key,count))
          }
        }
        case _=>
      }
    }
  }
}
