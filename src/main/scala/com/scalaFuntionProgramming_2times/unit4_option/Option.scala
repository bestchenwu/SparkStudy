package com.scalaFuntionProgramming_2times.unit4_option

sealed trait Option[+A] {

  /**
    * 如果不为None,则对其应用f
    *
    * @param f
    * @tparam B
    * @return
    */
  def map[B](f: A => B): Option[B] = this match {
    case Some(value) => Some(f(value))
    case None => None
  }

  /**
    * 如果option不为none,则应用f可能会失败
    *
    * @param f
    * @tparam B
    * @return
    */
  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case Some(value) => f(value)
    case None => None
  }

  /**
    * 如果当前是none，则返回B<br/>
    * 这里default: =>B 表示参数类型是B,但不是立即求值,当函数需要时候才求值
    *
    * @param default
    * @tparam B
    * @return
    */
  def getOrElse[B >: A](default: => B): B = this match {
    case Some(value) => value
    case None => default
  }

  /**
    * 如果当前为None,则返回option[B]<br/>
    * B>:A 表示B是A的父类型
    *
    * @param ob
    * @tparam B
    * @return
    */
  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case Some(_) => this
    case None => ob
  }

  /**
    * 如果值不满足f,转换some为none
    *
    * @param f
    * @return
    */
  def filter(f: A => Boolean): Option[A] = this match {
    case Some(value) => if (f(value)) this else None
    case None => None
  }

  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = a flatMap (aa => (b map (bb => f(aa, bb))))

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => None
    case h :: t => h flatMap (hh => sequence(t) map (hh :: _))
  }

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil => None
    case h :: t => map2(f(h), traverse(t)(f))(_ :: _)
  }

  /**
    * 利用traverse来实现sequence<br/>
    * 将Option[A]转换为Option[A]
    *
    * @param a
    * @tparam A
    * @return
    */
  def sequence1[A](a: List[Option[A]]): Option[List[A]] = traverse(a)(x => x)
}

case class Some[A](value: A) extends Option[A]

case object None extends Option[Nothing]

object Option {

  def mean(xs: Seq[Double]): Option[Double] = {
    if (xs.isEmpty) None
    else Some(xs.sum / xs.size)
  }

  def variance(xs: Seq[Double]): Option[Double] = {
    mean(xs) flatMap (m => mean(xs.map(i => Math.pow(i - m, 2))))
  }
}