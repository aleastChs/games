package example
import scalajs.js
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import dom.html

import scala.util.Random


@JSExport
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    // setup
    val renderer = canvas.getContext("2d")
                    .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = canvas.parentElement.clientWidth
    canvas.height = canvas.parentElement.clientWidth

    renderer.font = "50px sans-serif"
    renderer.textAlign = "center"
    renderer.textBaseline = "middle"
    
    /*Setup variables*/
    var snakeLength = 2  // Starting length
    var playerY = canvas.height / 2.0
    var playerX = canvas.width / 2.0
    var veloY = 0.0
    var veloX = 0.0
    var dead = 0

    /*GAME LOGIC*/

    def runLive(js.event) = {
      // create new obstacles 
      if (frame >= 0 && frame % obstacleGap == 0) {
        obstacles.enqueue(Random.nextInt(canvas.height - 2 * holeSize) + holeSize)
      }
      // if needed kill old
      if(obstacles.length > 7) {
        obstacles.dequeue()
        frame -= obstacleGap
      }

      // move player
      playerY += veloY
      playerX += veloX
      // set new velocit
      val e = event.which || event.keyCode

      // w-pressed
      if (e == 119 || e == 87) {
        veloY = -1
      } // a pressed
      else if (e == 65 || e == 97 ) {
        veloX = -1
      } // d pressed
      else if (e == 100 || e == 68) {
        veloX = 1
      } // s pressed
      else if (e == 115 || e == 83) {
        veloY = 1
      }

      // set fillstyle
      renderer.fillStyle = "darkblue"

      // loopa
      for((holeY, i) <- obstacles.zipWithIndex) {
        // beräkna hole i X-led, beror på vilken frame
        var holeX = i * obstacleGap - frame + canvas.width
        // display each obstacles as 
        renderer.fillRect(holeX, 0, 5, holeY - holeSize)
        renderer.fillRect(holeX, holeY + holeSize, 5, canvas.height - holeY - holeSize)

        // kill player
        if(math.abs(holeX - canvas.width/2) < 5 && math.abs(holeY - playerY) > holeSize ) dead = 50
      }

      // render player
      renderer.fillStyle = "darkgreen" 
      // display the player as a 10 * 10 px rectangle
      renderer.fillRect(canvas.width / 2 - 5, playerY - 5, 10, 10)
      // if out of bounds
      if (playerY < 0 || playerY > canvas.height) dead = 50
    }

    def runDead() = {
      playerY = canvas.height / 2
      playerVelo = 0
      frame = -50
      obstacles.clear()
      dead -= 1
      renderer.fillStyle = "darkred"
      renderer.fillText("Game Over", canvas.width / 2, canvas.height / 2)
    }
   

    def run() = {
      renderer.clearRect(0, 0, canvas.width, canvas.height)
      if (dead > 0) runDead()
      else runLive()
    }

    
    dom.window.setInterval(run _, 20)

    canvas.onclick = (e: dom.MouseEvent) => {
      playerVelo -= 5
    }
  }
}
