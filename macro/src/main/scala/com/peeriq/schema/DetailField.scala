package com.peeriq.schema

/**
  *
  * @param name
  * @param primitiveType
  * @param nullable
  * @param default
  */
case class DetailField(name: String, primitiveType: String, nullable: Int, default: AnyVal)
