package com.scalaFuntionProgramming_2times.unit15_Process

sealed trait Process[I, O] {

  /**
    * 这里的#:: 与List里面的::相似
    *
    * @param s
    * @return
    */
  def apply(s: Stream[I]): Stream[O] = this match {
    case Halt() => Stream()
    case Await(recv) => s match {
      case h #:: t => recv(Some(h))(t)
      case xs => recv(None)(xs)
    }
    case Emit(head, tail) => head #:: tail(s)
  }

  def repeat: Process[I, O] = {

    def go(p: Process[I, O]): Process[I, O] = p match {
      case Halt() => go(this)
      case Await(recv) => Await {
        case None => recv(None)
        case Some(i) => go(recv(Some(i)))
      }
      case Emit(h, t) => Emit(h, go(t))
    }

    go(this)
  }

  def filter(f: I => Boolean): Process[I, I] = Await[I, I] {
    case Some(i) if (f(i)) => Emit(i)
    case _ => Halt()
  }.repeat

  //todo:期望是两个处理并起来，但是结果不是这样
  def ++(p: Process[I, O]): Process[I, O] = this match {
    case Halt() => p
    case Emit(h, t) => Emit(h, t ++ p)
    case Await(recv) => Await(recv andThen (_ ++ p))
  }
}

//将数据输送到下一个输出流
case class Emit[I, O](head: O, tail: Process[I, O] = Halt[I, O]()) extends Process[I, O]

//从输入流获得下一个值，并传递下一个输出流，一旦没有值进来，则可以给None,用于终止
case class Await[I, O](recv: Option[I] => Process[I, O]) extends Process[I, O]

//暂停流
case class Halt[I, O]() extends Process[I, O]

object Process {

  /**
    * 由于这里的Emit()里的下一个输出流是Halt，所以无法获得下一个stream的输出值
    *
    * @param f
    * @tparam I
    * @tparam O
    * @return
    */
  def liftOne[I, O](f: I => O): Process[I, O] = Await {
    case Some(i) => Emit(f(i))
    case None => Halt()
  }

  def lift[I, O](f: I => O): Process[I, O] = liftOne(f).repeat

  def sum: Process[Double, Double] = {
    def go(acc: Double): Process[Double, Double] = {
      Await[Double, Double] {
        case Some(d) => Emit(d + acc, go(d + acc))
        case None => Halt()
      }
    }

    go(0.0)
  }

  def take[I](n: Int): Process[I, I] = {
    def go(num: Int): Process[I, I] = Await {
      case Some(i) if num < n => Emit(i, go(num + 1))
      case _ => Halt()
    }

    go(0)
  }

  def takeWhile[I](f: I => Boolean): Process[I, I] = {

    def go(f: I => Boolean): Process[I, I] = Await[I, I] {
      case Some(i) if f(i) => Emit(i, go(f))
      case Some(i) if !f(i) => go(f)
      case None => Halt()
    }

    go(f)
  }

  def dropWhile[I](f: I => Boolean): Process[I, I] = {

    def go(f: I => Boolean): Process[I, I] = Await[I, I] {
      case Some(i) if f(i) => go(f)
      case Some(i) if !f(i) => Emit(i, go(f))
      case None => Halt()
    }

    go(f)
  }

  def count[I]: Process[I, Int] = {

    def go(sum: Int): Process[I, Int] = Await[I, Int] {
      case Some(_) => Emit(sum, go(sum + 1))
      case None => Halt[I, Int]()
    }

    go(0)
  }

  def mean: Process[Double, Double] = {

    def go(sum: Double, count: Int): Process[Double, Double] = Await[Double, Double] {
      case Some(d) => Emit((sum + d) / count, go(sum + d, count + 1))
      case None => Halt()
    }

    go(0, 1)
  }

  def exists[I](f: I => Boolean): Process[I, Boolean] = {
    def go(f: I => Boolean): Process[I, Boolean] = Await {
      case Some(i) if (f(i)) => Emit(true)
      case Some(i) if (!f(i)) => go(f)
      case None => Emit(false)
    }

    go(f)
  }
}

