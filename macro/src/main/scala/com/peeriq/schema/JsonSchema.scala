package com.peeriq.schema

import java.io.FileInputStream

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object JsonSchema {

  private lazy val jsonMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  /**
    * Load schema from Json
    *
    * @param fileName
    * @return
    */
  def fromJson(fileName: String): JsonSchema = {
    val inStream = new FileInputStream(fileName)
    try {
      jsonMapper.readValue(inStream, classOf[JsonSchema])
    } finally {
      inStream.close()
    }
  }

  /**
    * Write schema out to Json
    *
    * @param schema
    * @return
    */
  def toJson(schema: JsonSchema): String = {
    jsonMapper.writeValueAsString(schema)
  }

}

case class JsonSchema(name: TypeName, comment: String, fields: Seq[Field])
