package com.peeriq.driver

import com.peeriq.macros.FromSchema
import com.peeriq.schema.TypeSchema

@FromSchema("driver/src/main/resources/test.json") class Test

object Main extends App {

  println("Inside driver")

  val schema = TypeSchema.fromJson("driver/src/main/resources/test.json")
  println(schema)

  val t = Test("test-1", 2)

}
