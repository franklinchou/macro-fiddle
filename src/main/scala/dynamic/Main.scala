package dynamic

import dynamic.macros.FromSchema
import dynamic.schema.TypeSchema

@FromSchema("macro-fiddle/src/main/resources/test.json") class Test
object Main extends App {

  val schema = TypeSchema.fromJson("macro-fiddle/src/main/resources/test.json")

  println(schema.fields)

}
