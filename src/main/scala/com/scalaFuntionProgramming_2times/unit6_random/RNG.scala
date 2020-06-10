package com.scalaFuntionProgramming_2times.unit6_random

sealed trait RNG {

  //这称为状态转移或者状态行为
  type RAND[+A] = RNG => (A, RNG)

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

  def randomDouble: RAND[String] = map(nonNegativeInt)(item=>(item.asInstanceOf[Double]/ Int.MaxValue).formatted("%1.3f"))

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
