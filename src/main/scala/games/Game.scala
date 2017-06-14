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
    canvas.height = canvas.parentElement.clientHeight

    renderer.font = "50px sans-serif"
    renderer.textAlign = "center"
    renderer.textBaseline = "middle"
    
    /*Setup variables*/
    var snakeLength = 10  // Starting length
    var snakeBars = 1
    // Starting Position
    var playerY = canvas.height / 2.0
    var playerX = canvas.width / 2.0
    var veloY = 0.0
    var veloX = 0.0
    var dead = 0

    // List of each obstacle, storing only the Y position of the hole.
    // The X position of the obstacle is calculated by its position in the
    // queue and in the current frame.
    val obstacles = collection.mutable.Queue.empty[Int]

    /*GAME LOGIC*/

    def runLive() = {
      // create new obstacles 
      def createNew(): Unit = {
        //obstacles.enqueue(Random.nextInt(canvas.height))
      }

      // move player
      playerY += veloY
      playerX += veloX
      
      
      // set background
      renderer.fillStyle = "black"

      renderer.fillRect(0, 0, canvas.width, canvas.height)

      // render player
      renderer.fillStyle = "yellow" 
      // display the snake as a 3 * snakeLength px rectangle
      renderer.fillRect(playerX, playerY, 3, snakeLength)
      // if out of bounds
      if (playerY < 0 || playerY > canvas.height) dead = 50
      else if (playerX < 0 || playerY > canvas.width) dead = 50
    }

    def runDead() = {
      playerY = canvas.height / 2.0
      playerX = canvas.width / 2.0
      veloY = 0
      veloX = 0
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

    canvas.onkeydown = (e: dom.KeyboardEvent) => {
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
    }
  }
}
