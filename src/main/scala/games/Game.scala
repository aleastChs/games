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


    val listings = Seq(
      "Apple", "Apricot", "Banana", "Cherry",
      "Mango", "Mangosteen", "Mandarin",
      "Grape", "Grapefruit", "Guava"
    )

    def renderListings = ul(
      for {
        fruit <- listings
        if fruit.toLowerCase.startsWith(
          box.value.toLowerCase
        )
      } yield {
        val (first, last) = fruit.splitAt(
          box.value.length
        )
        li(
          span(
            backgroundColor:="yellow",
            first
          ),
          last
        )
      }
    ).render

    val output = div(renderListings).render

    box.onkeyup = (e: dom.Event) => {
      output.innerHTML = ""
      output.appendChild(renderListings)
    }

    target.appendChild(
      div(
        h1("Search Box!"),
        p(
          "Type here to filter " +
            "the list of things below!"
        ),
        div(box),
        output
      ).render
    )
  }
}
