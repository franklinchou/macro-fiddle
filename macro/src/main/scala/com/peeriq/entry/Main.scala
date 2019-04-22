package com.peeriq.entry

import com.peeriq.schema.TypeSchema

object Main extends App {

  val schema = TypeSchema.fromJson("src/main/resources/test.json")

  println(schema.fields)

}
