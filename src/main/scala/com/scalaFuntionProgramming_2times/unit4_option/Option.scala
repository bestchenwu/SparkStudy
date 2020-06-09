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
}

case class Some[A](value: A) extends Option[A]

case object None extends Option[Nothing]

object Option {

}