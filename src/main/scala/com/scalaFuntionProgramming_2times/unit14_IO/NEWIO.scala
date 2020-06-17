package com.scalaFuntionProgramming_2times.unit14_IO

object NEWIO {

  sealed trait NEWIO[A] {
    def flatMap[B](f: A => NEWIO[B]): NEWIO[B] = FlatMap(this, f)

    def map[B](f: A => B): NEWIO[B] = flatMap(f andThen (Return(_)))
  }

  def printLine(s: String): NEWIO[Unit] = Suspend(() => Return(println(s)))

  /** Return 代表IO动作完成 **/
  case class Return[A](a: A) extends NEWIO[A]

  /** Suspend指我们想执行某些作用来产生结果 **/
  case class Suspend[A](resume: () => A) extends NEWIO[A]

  /** FlatMap可以继续延伸计算 **/
  case class FlatMap[A, B](sub: NEWIO[A], f: A => NEWIO[B]) extends NEWIO[B]

  @annotation.tailrec
  def run[A](io: NEWIO[A]): A = io match {
    case Return(a) => a
    case Suspend(r) => r()
    case FlatMap(x, f) => x match {
      case Return(a) => run(f(a))
      case Suspend(r) => run(f(r()))
      case FlatMap(y, g) => run(y flatMap (a => g(a) flatMap f))
    }
  }
}



