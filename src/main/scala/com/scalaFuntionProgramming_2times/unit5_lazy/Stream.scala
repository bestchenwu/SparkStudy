package com.scalaFuntionProgramming_2times.unit5_lazy

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

sealed trait Stream[+A] {

  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, _) => Some(h())
  }

  def toList: List[A] = this match {
    case Empty => List()
    case Cons(h, t) => h() :: t().toList
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if (n > 1) => Cons(() => h(), () => t().take(n - 1))
    case Cons(h, _) if n <= 1 => Cons(() => h(), () => Empty)
    case _ => Empty
  }

  def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if (n > 1) => t().drop(n - 1)
    case Cons(_, t) if (n <= 1) => t()
    case _ => Empty
  }

  def takeWhile(f: A => Boolean): Stream[A] = this match {
    case Cons(h, t) => if (f(h())) t().takeWhile(f) else Cons(() => h(), () => t().takeWhile(f))
    case _ => Empty
  }

  def exists(f: A => Boolean): Boolean = this match {
    case Cons(h, t) => f(h()) || t().exists(f)
    case _ => false
  }

  def foldRight[B](z: B)(f: (A, => B) => B): B = {
    this match {
      case Empty => z
      case Cons(h, t) => f(h(), t().foldRight(z)(f))
    }
  }

  //这里如果f(a)为true,就不需要继续循环b了。
  def existsViaFoldRight(f: A => Boolean): Boolean = foldRight(false)((a, b) => f(a) || b)

  /**
    * 检测stream里是否每个元素都符合p
    *
    * @param p
    * @return
    */
  def forAll(p: A => Boolean): Boolean = this match {
    case Empty => true
    case Cons(h, t) if (!p(h())) => false
    case Cons(h, t) if (p(h())) => t().forAll(p)
  }

  def forAllViaFoldRight(p: A => Boolean): Boolean = foldRight(true)((a, b) => p(a) && b)

  def headOptionViaFoldRight: Option[A] = foldRight(None: Option[A])((a, _) => Some(a))

//  def startWith[A](otherStream: Stream[A]): Boolean = otherStream.forAll(item => {
//      val headOption = this.drop(1).headOption
//      if(headOption.isEmpty){
//          false
//      }else if(headOption.get.equals(item)){
//        false
//      }else{
//        true
//      }
//  })
}

case object Empty extends Stream[Nothing]

//这里的h和t都是非严格求值
//由于scala的case class限制,这里必须接受一个无参但返回A类型的函数
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {

  val ones: Stream[Int] = cons(1, ones)

  def empty[A]: Stream[A] = Empty

  def cons[A](h: => A, t: => Stream[A]): Stream[A] = {
    lazy val hh = h
    lazy val tt = t
    Cons(() => hh, () => tt)
  }

  def apply[A](as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  /**
    * 生成指定常量的无限流
    *
    * @param value
    * @tparam A
    * @return
    */
  def constant[A](value: A): Stream[A] = cons(value, constant(value))

  /**
    * 生成从n开始,然后n+1,n+2的整数无限流
    *
    * @param n
    * @return
    */
  def from(n: Int): Stream[Int] = cons(n, from(n + 1))

  def fibs: Stream[Int] = {

    def loop(i: Int, j: Int): Stream[Int] = cons(i, loop(j, i + j))

    loop(0, 1)
  }

  /**
    * 利用一个初始状态生成下一个状态<br/>
    * 当得到的状态是empty的时候 终止流<br/>
    * 这种方式称为共递归也称为守护递归
    *
    * @param z
    * @param f
    * @tparam A
    * @tparam S
    * @return
    */
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
    val arrayBuffer: ArrayBuffer[A] = ArrayBuffer[A]()

    def loop(z: S): Stream[A] = {
      val option = f(z)
      if (option.isEmpty) {
        Stream.apply(arrayBuffer: _*)
      } else {
        val currentResult = option.get
        arrayBuffer.append(currentResult._1)
        loop(currentResult._2)
      }
    }

    loop(z)
  }

  def unfold1[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
    f(z) match {
      case None => Empty
      case Some((a, s)) => cons(a, unfold1(s)(f))
    }
  }

  def onesViaUnfold: Stream[Int] = unfold1(1)((x: Int) => Some((1, x)))

  def fromIntViaFold(n: Int): Stream[Int] = unfold1(n)(n => Some(n, n + 1))

  //todo:这里为啥用case就可以了
  def fibsViaFold: Stream[Int] = unfold1((0, 1)) { case (i: Int, j: Int) => Some(i, (j, i + j)) }


}
