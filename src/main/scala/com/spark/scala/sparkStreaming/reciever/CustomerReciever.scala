package com.spark.scala.sparkStreaming.reciever

import java.io.{BufferedReader, IOException, InputStreamReader}
import java.net.Socket

import org.apache.spark.internal.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

class CustomerReciever(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_AND_DISK) with Logging {
  override def onStart(): Unit = {
    new Thread("CustomerReciever thread") {
      override def run(): Unit = {
        recieve()
      }
    }.start()

  }

  override def onStop(): Unit = {

  }

  private def recieve(): Unit = {
    try {
      val socket = new Socket(host, port)
      val inputStream = socket.getInputStream
      val reader = new InputStreamReader(inputStream)
      val bufferedReader = new BufferedReader(reader)
      var line = bufferedReader.readLine()
      while (!isStopped() && line != null) {
        println(s"recieve message:{$line}")
        store(line)
        line = bufferedReader.readLine()
      }
      bufferedReader.close()
      inputStream.close()
    } catch {
      case e: IOException => {
        throw e
      }
      case t: Throwable => {
        restart(t.getMessage, t)
      }
    }

  }
}
