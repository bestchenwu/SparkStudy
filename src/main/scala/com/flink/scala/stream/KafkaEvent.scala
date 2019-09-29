package com.flink.scala.stream

case class KafkaEvent(val id:String,val value:Long,val timestamp:Long) {}
