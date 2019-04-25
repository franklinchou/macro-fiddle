package com.peeriq.schema

case class SQL2ScalaMapper(sqlTypeAsString: String) {

  // This should be Refined type
  def apply: String = {
    val numericRE = """numeric(\*)"""".r()
    sqlTypeAsString match {
      case "varchar" => "String"
      case "date" => "LocalDate"
      case "boolean" => "Boolean"
      case numericRE(_*) => "BigDecimal"
    }
  }

}
