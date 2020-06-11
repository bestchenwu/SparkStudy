package com.scalaFuntionProgramming_2times.unit6_random

case class State[S, A](run: S => (A, S)) {

  def unit[A](a: A): State[S, A] = State(s => (a, s))
  def map[A, B](stateA: State[S, A])(f: A => B): State[S, B] = State(s => {
    val (aa, ss) = stateA.run(s)
    (f(aa), ss)
  })

  def map2[A, B, C](ra: State[S, A], rb: State[S, B])(f: (A, B) => C): State[S, C] = State(s => {
    val (aa, _) = ra.run(s)
    val (bb, _) = rb.run(s)
    (f(aa, bb), s)
  })

  def flatMap[A, B](f: State[S, A])(g: A => State[S, B]): State[S, B] = State(s => {
    val (aa, _) = f.run(s)
    val stateB = g(aa)
    stateB.run(s)
  })

  def sequence[A](fs: List[State[S, A]]): State[S, List[A]] = State(s => {
    fs match {
      case Nil => (List(), s)
      case h :: t => {
        val (aa, _) = h.run(s)
        val (tailList, _) = sequence(t).run(s)
        (aa :: tailList, s)
      }
    }
  })
}

object State {

}

trait RNG {

  //这称为状态转移或者状态行为
  type RAND[+A] = RNG => (A, RNG)

  type STATE_RAND[A] = State[RNG, A]

  val int: RAND[Int] = _.nextInt

  //总是返回常量值的状态
  def unit[A](a: A): RAND[A] = rng => (a, rng)

  def nextInt: (Int, RNG)

  def randomPair(rng: RNG): ((Int, Int), RNG)

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (i, r) = rng.nextInt
    (if (i < 0) -(i + 1) else i, r)
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = count match {
    case n if (n <= 0) => (List(), rng)
    case n => {
      val (currentInt, rng2) = rng.nextInt
      val tuple = ints(n - 1)(rng2)
      (currentInt :: tuple._1, tuple._2)
    }
  }

  def map[A, B](s: RAND[A])(f: A => B): RAND[B] = {
    rng => {
      val (a, rng1) = s(rng)
      (f(a), rng1)
    }
  }

  def randomDouble: RAND[String] = map(nonNegativeInt)(item => (item.asInstanceOf[Double] / Int.MaxValue).formatted("%1.3f"))

  def map2[A, B, C](ra: RAND[A], rb: RAND[B])(f: (A, B) => C): RAND[C] = rng => {
    val (aa, rng1) = ra(rng)
    val (bb, _) = rb(rng)
    (f(aa, bb), rng1)
  }

  def both[A, B](ra: RAND[A], rb: RAND[B]): RAND[(A, B)] = map2(ra, rb)((_, _))

  def sequence[A](fs: List[RAND[A]]): RAND[List[A]] = rng => {
    fs match {
      case Nil => (List(), rng)
      case h :: t => val tuple = h(rng); (tuple._1 :: sequence(t)(tuple._2)._1, tuple._2)
    }
  }

  def flatMap[A, B](f: RAND[A])(g: A => RAND[B]): RAND[B] = rng => {
    val (aa, rng1) = f(rng)
    val randB = g(aa)
    randB(rng1)
  }

  def mapViaFlatMap[A, B](s: RAND[A])(f: A => B): RAND[B] = flatMap(s)((a: A) => unit(f(a)))

  def map2ViaFlatMap[A, B, C](ra: RAND[A], rb: RAND[B])(f: (A, B) => C): RAND[C] = flatMap(ra)(a => map(rb)(b => f(a, b)))
}

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
    val nextRNG = SimpleRNG(newSeed) // The next state, which is an `RNG` instance created from the new seed.
    val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
    (n, nextRNG)
  }

  override def randomPair(rng: RNG): ((Int, Int), RNG) = {
    val (int1, rng1) = rng.nextInt
    val (int2, rng2) = rng1.nextInt
    ((int1, int2), rng2)
  }

  def double(rng: RNG): (Double, RNG) = {
    val (int1, rng1) = nonNegativeInt(rng)
    ((int1 / Int.MaxValue).asInstanceOf[Double], rng1)
  }


}
