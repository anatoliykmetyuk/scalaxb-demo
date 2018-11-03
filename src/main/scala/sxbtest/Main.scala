package sxbtest

import cats._, cats.implicits._

import www.example.com.IPO._
import sxbtest.protocol._

// Adapted from http://scalaxb.org/running-scalaxb
object Main extends MainHelpers {
  def main(args: Array[String]): Unit = {
    val shipTo = scalaxb.fromXML[Address](subject)
    val document = scalaxb.toXML[Address](
      shipTo.copy(name = "Bar"), None, Some("foo"), defaultScope)

    println(shipTo)
    println(document)
  }
}

trait MainHelpers {
  val subject = <shipTo xmlns="http://www.example.com/IPO">
    <name>Foo</name>
    <street>1537 Paper Street</street>
    <city>Wilmington</city>
  </shipTo>
}
