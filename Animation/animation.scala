import doodle.core._
import doodle.image._
import doodle.image.syntax._
import doodle.image.syntax.core._
import doodle.java2d._
import doodle.reactor._
import scala.concurrent.duration._
import scala.io.StdIn

object animation {
  val frame = Frame.size(2000, 1000)

  val fastestTime = 42.1
  val x_sparkBall = fastestTime / 42.1   
  val x_polarsBall = fastestTime / 106.28 
  val x_hadoopBall = fastestTime / 105.23 
  val x_beamBall = fastestTime / 300.0    
  val x_pandasBall = fastestTime / 165.21 
  val x_pysparkBall = fastestTime / 135.09 

  val animate = Reactor
    .init(Vec(-300, 0))
      .onTick(pt => 
        if(pt.x > 370) Vec(-300, pt.y)
        else Vec(pt.x + 10, pt.y)
      )
      .render { pt => 
        val basePosition = pt.x + 300
        val sparkBall = (Vec(basePosition * x_sparkBall, 200), Color.red)
        val polarsBall = (Vec(basePosition * x_polarsBall, 100), Color.black)
        val hadoopBall = (Vec(basePosition * x_hadoopBall, 0), Color.yellow)
        val beamBall = (Vec(basePosition * x_beamBall, -100), Color.orange)
        val pandasBall = (Vec(basePosition * x_pandasBall, -200), Color.blue)
        val pysparkBall = (Vec(basePosition * x_pysparkBall, -300), Color.green)
        val points = List(sparkBall, polarsBall, hadoopBall, beamBall, pandasBall, pysparkBall)
        points.map { case (new_pt, color) =>
          Image.circle(65).fillColor(color).at(new_pt)
        }.foldLeft(Image.empty)(_ on _)
      }

    def main(args: Array[String]): Unit = {
      animate.run(frame)

      StdIn.readChar()
    }
}
