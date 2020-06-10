package com.scalaFuntionProgramming_2times.unit4_option

sealed trait Either[+E, +A] {

  def mean(xs: Seq[Double]): Either[String, Double] = {
    if (xs.isEmpty) Left("xs is empty")
    else Right(xs.sum / xs.size)
  }

  def Try[A](a: => A): Either[Exception, A] = {
    try {
      Right(a)
    } catch {
      case e: Exception => Left(e)
    }
  }

  def map[B](f: A => B): Either[E, B] = this match {
    case Right(value) => Right(f(value))
    case Left(exception) => Left(exception)
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Right(value) => f(value)
    case Left(e) => Left(e)
  }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Right(value) => Right(value)
    case Left(e) => b
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = for {
    aa <- this
    bb <- b
  } yield f(aa, bb)

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match {
    case Nil => Right(Nil)
    case h :: t => h flatMap (hh => sequence(t) map (hh :: _))
  }

  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = as match {
    case Nil => Right(Nil)
      //todo:为什么这里的(_::_)要放在函数的最后面
    case h :: t => (f(h) map2 (traverse(t)(f))) (_ :: _)
  }

}

/**
  * left 用于表示失败
  *
  * @param value
  * @tparam E
  */
case class Left[+E](value: E) extends Either[E, Nothing]

/**
  * right 表示成功
  *
  * @param value
  * @tparam A
  */
case class Right[+A](value: A) extends Either[Nothing, A]
