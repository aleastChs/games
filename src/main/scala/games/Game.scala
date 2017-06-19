package games
import scala.scalajs
import scalajs.js
import js.annotation.JSExport
import org.scalajs.dom
import dom.html
import scalatags.JsDom
import JsDom.all._

import scala.util.Random


@JSExport
object Game extends{
  @JSExport
  def main(target: html.Div) = {
    val box = input(
      `type`:="text",
      placeholder:="Type here!"
    ).render

    val output = span.render

    box.onkeyup = (e: dom.Event) => {
      output.textContent =
        box.value.toUpperCase
    }

    target.appendChild(
      div(
        h1("Capital Box!"),
        p(
          "Type here and " +
            "have it capitalized!"
        ),
        div(box),
        div(output)
      ).render
    )
  }
}
