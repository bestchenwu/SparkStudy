package com.flink.scala2.watermark

case class MyEvent[T](val time:Long,val data:T)
