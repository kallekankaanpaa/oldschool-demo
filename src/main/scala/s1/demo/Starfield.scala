package s1.demo

import s1.image.ImageExtensions._

import scala.util.Random
import scala.collection.mutable

object Starfield extends Effect(500, 500) {
  val random = new Random
  var clock = 0

  val pic = emptyImage
  val graphics = pic.graphics
  val originTop = 250
  val originLeft = 250
  val speed = 4
  val maxDepth = 150

  case class Star(x: Double, y: Double, z: Int)

  val stars = mutable.Buffer.tabulate(300)(i => Star(random.nextInt(200)-100, random.nextInt(200)-100, random.nextInt(maxDepth)))



  /**
   * Here we draw a BufferedImage on the current state of the [[Effect]]
   */
  def makePic() = {
    graphics.setColor(java.awt.Color.black)
    graphics.fillRect(0, 0, 500, 500)
    // sort the bobs by z
    for (star <- stars.sortBy(-_.z)) {
      val grayValue = ((1 - math.pow(star.z / maxDepth.toDouble, 2)) * 255).toInt // calculates normalized value for gray depending on the z value of the star
      graphics.setColor(new java.awt.Color(grayValue, grayValue, grayValue, 255))
      graphics.fillOval(
        originLeft + (star.x*256/star.z).toInt, // x (left top corner)
        originTop + (star.y*256/star.z).toInt, // y (left top corner)
        (10 - star.z * 0.05).toInt, // width scaled by distance
        (10 - star.z * 0.05).toInt) // height scaled by distance
    }
    // Finally we return the picture we created.
    pic
  }

  /**
   * Here we modify the state (the in this example case only the time)
   */
  def changeThings() = {
    clock += 1

    stars.transform(star => {
      val newZ = star.z - speed
      if(newZ <= 1) newStar
      else rotate(0.5, Star(star.x, star.y, newZ))
    })

    def newStar = Star(random.nextInt(200) - 100, random.nextInt(200) - 100, maxDepth)

    def rotate(spinningSpeed: Double, star: Star) = {
      val cos = math.cos(spinningSpeed/10)
      val sin = math.sin(spinningSpeed/10)
      Star(
        star.x * cos - star.y * sin,
        star.y * cos + star.x * sin,
        star.z
      )
    }
  }

  /**
   * Checks whether the effect is finished.
   *
   * @return true when this effect is over, false otherwise
   */
  def next = clock > 300

}
