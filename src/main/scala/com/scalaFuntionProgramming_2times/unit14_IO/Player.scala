package com.scalaFuntionProgramming_2times.unit14_IO

case class Player(name: String, score: Int)

object Player {


}

object PlayerUtil {

  def winner(p1: Player, p2: Player): Option[Player] = {
    if (p1.score > p2.score) {
      Some(p1)
    } else if (p1.score < p2.score) {
      Some(p2)
    } else {
      None
    }
  }

  def contest(p1: Player, p2: Player): String = winner(p1, p2) match {
    case Some(player) => s"${player.name} is winner"
    case None => "it's a draw"
  }
}
