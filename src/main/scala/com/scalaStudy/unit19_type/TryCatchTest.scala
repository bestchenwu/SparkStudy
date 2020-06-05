package com.scalaStudy.unit19_type

import scala.util.{Failure, Try}


/**
  * 自定义try Catch
  *
  * @author chenwu on 2020.6.5
  */
sealed class Attempt[A]

case class Succeed[A](val value: A) extends Attempt[A]

case class Failed[A](val exception: Exception) extends Attempt[A]

object Attemt {

  def apply[A](f: A=> A)(x:A) = {
    try {
      val result = f(x)
      Succeed(result)
    } catch {
      case e: Exception => Failed(e)
    }
  }
}

object TryCatchTest {

  def main(args: Array[String]): Unit = {

    def divideFunction = (x:Int)=>42/x

    val result = Attemt.apply(divideFunction)(0)
    println(result)

    //官方版的try catch
    val tryResult = Try(divideFunction(0))
    println(tryResult)

    def divideEither(x:Int,y:Int):Either[Int,String]={
        Try(x/y) match{
          case scala.util.Success(value) => Left(value)
          case Failure(e) => Right(e.getMessage)
        }
    }

    val a = divideEither(2,0)
    val b = divideEither(6,2)
    println(a)
    println(b)
  }
}
