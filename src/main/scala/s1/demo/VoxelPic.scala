package s1.demo
import java.awt.{BasicStroke, Color}

import javax.imageio.ImageIO
import java.io.File
import java.awt.Color._

import scala.math.{abs, min,max}
import s1.image.ImageExtensions._

import scala.collection.mutable.Buffer

object VoxelPic extends Effect(500,500){

  //println("Debug: Animation calculation started")
  var clock = 0
  val img = ImageIO.read(new File("pictures/perlinNoise1.png"))
  val w = img.getWidth
  val h = img.getHeight
  var vek: Vector[Vector[(Int, Color)]] = Vector() // Vektori johon tallenetaan alkuperäiset korkeus ja väri arvot (Int, Color)-muodossa.
  var startY = 150 // kuinka paljon tyhjää halutaan ruudun yläreunaan alussa

  // Tuo kuvasta korkeus tiedot
  for (y <- 0 until h) {
    var tempBuf: Buffer[Color] = Buffer()
    for (x <- 0 until w) {
       tempBuf = tempBuf :+ new Color(img.getRGB(x,y))
    }
    vek = vek :+ tempBuf.map(n => colorMapper(n)).toVector
  }

  // tämän vektorin päivitys (metodi: changeThings()) saa maiseman liikkumaan. Tässä annetaan lähtö arvo
  var tempVek = vek.take(220)

  /** Palauttaa korkeudelle sopivan värin */
  def colorMapper(color: Color) = {
    val int = (255-((color.getRed+ color.getGreen+ color.getBlue)/3))
    if (int < 50) {
      (50, new Color(84,135,222))
    } else if ( int < 70) {
      val temp = (int-50)*3/2
      (int, new Color(184+temp,134+temp,11+temp))
    } else {
      val temp = (int-70)
      (int, new Color(temp,50+temp,0))
    }
  }

  //println("Debug: Colors and heights calculated.")
  def makePic() = {
    // Get an empty space where to draw
    val pic      = emptyImage

    // Get the tools to draw with
    val graphics = pic.graphics

    var yCoord = startY
    for (y <- tempVek) {
      var xCoord = 0
      for (x <- y) {
        graphics.setColor(x._2)
        graphics.setStroke(new BasicStroke(4))
        graphics.fillRect(xCoord, (yCoord-x._1/2),6,x._1/2)
        xCoord +=6
      }
       yCoord +=2
    }
    //println("Debug: Pic #" + clock)
    pic
  }

  /**
   * Here we modify the state
   */
  def changeThings() = {
    var tempSize = min(200+clock, 270)
    var toDrop = if (clock < 75) 0 else (clock-75)
    startY = max(startY - 2, 0)
    clock += 1

    tempVek = vek.drop(toDrop).take(tempSize)



  }

  def next = clock > 330



}
