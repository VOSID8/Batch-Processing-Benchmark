import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.reactor._
import scala.concurrent.duration._

import scala.io.StdIn

object Example {
  val frame = Frame.size(600, 600)

  val travellingCircle = Reactor.init(Vec(-300, 0))
    .onTick(pt => Vec(pt.x + 1, pt.y))
    .render(pt => Image.circle(50).fillColor(Color.blue).at(pt))
    .stop(pt => pt.x >= 300)

  val travellingCircle2 = 
    Reactor
    .init(Vec(-300, 100))
    .onTick(pt => Vec(pt.x + 1, pt.y))
    .render(pt => Image.circle(50).fillColor(Color.red).at(pt))
    .init(Vec(-300, 0))
    .onTick(pt => Vec(pt.x + 1, pt.y))
    .render(pt => Image.circle(50).fillColor(Color.blue).at(pt))
    .stop(pt => pt.x >= 300)

  def main(args: Array[String]): Unit = {
    travellingCircle.run(frame)
    travellingCircle2.run(frame)

    StdIn.readChar()
  }
}