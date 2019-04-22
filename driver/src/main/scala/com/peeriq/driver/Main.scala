package com.peeriq.driver

import java.time.LocalDate

import com.peeriq.macros.FromSchema
import com.peeriq.schema.TypeSchema

@FromSchema("/home/franklin/Documents/dev/macro-fiddle/driver/src/main/resources/test.json") class Test

object Main extends App {

  println("Inside driver")

  val schema = TypeSchema.fromJson("driver/src/main/resources/test.json")
  println(schema)

  // This doesn't compile (not found: value Test)
  val t = Test("test-1", 2, LocalDate.of(2019, 4, 22))

  // println(t.param3)

  println(t.param3.toString)

}
