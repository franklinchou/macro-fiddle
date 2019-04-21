package dynamic

import dynamic.macros.FromSchema
import dynamic.schema.TypeSchema

@FromSchema("macro-fiddle/src/main/resources/test.json") class Test
object Main extends App {

  val schema = TypeSchema.fromJson("src/main/resources/test.json")

  println(schema.fields)

  val t = Test("1", 2)

  println(t)

}
