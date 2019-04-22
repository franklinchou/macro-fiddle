package com.peeriq.schema

import java.io.FileInputStream

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object TypeSchema {

  private val mapper = new ObjectMapper().registerModule(DefaultScalaModule)

  /**
    * Load schema from Json
    *
    * @param fileName
    * @return
    */
  def fromJson(fileName: String): TypeSchema = {
    val inStream = new FileInputStream(fileName)
    try {
      mapper.readValue(inStream, classOf[TypeSchema])
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
  def toJson(schema: TypeSchema): String = {
    mapper.writeValueAsString(schema)
  }

}

case class TypeSchema(name: TypeName, comment: String, fields: Seq[Field])
