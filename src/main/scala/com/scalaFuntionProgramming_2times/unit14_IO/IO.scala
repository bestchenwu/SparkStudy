package com.scalaFuntionProgramming_2times.unit14_IO

import com.scalaFuntionProgramming_2times.unit12_Monad.Mon

/**
  * 通过这样的定义,IO类符合了Moniad原则。即:
  * 1、op(x,zero)==op(zero,x)
  * 2、op(x,op(y,z))== op(op(x,y),z)
  */
sealed trait IO[A] {
  self =>
  def run: A

  def map[B](f: A => B): IO[B] = new IO[B] {
    override def run: B = f(self.run)
  }

  def flatMap[B](f: A => IO[B]): IO[B] = new IO[B] {
    override def run: B = f(self.run).run
  }
}

/**
  * 现在组成了Monad原则 即具备了identity原则以及associative结合原则
  */
object IO extends Mon[IO] {
  override def unit[A](a: => A): IO[A] = new IO[A] {
    override def run: A = a
  }

  override def flatMap[A, B](fa: IO[A])(f: A => IO[B]): IO[B] = f(fa.run)

  def apply[A](a: => A): IO[A] = unit(a)

  def readFromLine: String = {
    scala.io.StdIn.readLine()
  }

  def ReadLine: IO[String] = IO(readFromLine)

  def PrintLine(msg: String): IO[Unit] = IO(println(msg))

  def Convert: IO[Unit] = for {
    _ <- PrintLine("please input a number")
    number <- ReadLine.map(_.toDouble)
    _ <- PrintLine(f"${number*9.9}%1.3f")
  } yield ()
}

//
//object IO {
//
//  def empty: IO = new IO {
//    override def run: Unit = {}
//  }
//}
