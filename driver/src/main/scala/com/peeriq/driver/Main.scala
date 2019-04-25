package com.peeriq.driver

import com.peeriq.macros.FromSchema
import com.peeriq.schema.CsvSchema

@FromSchema("/home/franklin/Documents/dev/macro-fiddle/driver/src/main/resources/test.csv") class Test

object Main extends App {

  println("Inside driver")

  // val schema = JsonSchema.fromJson("driver/src/main/resources/test.json")
  val schema = CsvSchema.fromCsv("driver/src/main/resources/test.csv")
  println(schema)

  val t = Test("test_originator", "test_originator_loan_id")
  
  println(t.originator_loan_id.toString)

}
