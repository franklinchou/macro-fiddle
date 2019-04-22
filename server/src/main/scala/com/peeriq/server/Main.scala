package main.scala.com.peeriq.server

import com.peeriq.macros.FromSchema
import com.peeriq.schema.TypeSchema


@FromSchema("macro-fiddle/src/main/resources/test.json") class Test
object Main extends App {

  val schema = TypeSchema.fromJson("src/main/resources/test.json")

  println(schema.fields)

  // Compiler is not finding the Test case class
  val t = Test("1", 2)

}
