package com.peeriq.schema

import java.io.FileInputStream

import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.dataformat.csv.{CsvMapper, CsvParser}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.collection.mutable.ListBuffer

object CsvSchema {

  private lazy val csvMapper =
    new CsvMapper()
      .enable(CsvParser.Feature.WRAP_AS_ARRAY)
      .enable(CsvParser.Feature.SKIP_EMPTY_LINES)
      .enable(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS)
      .registerModule(DefaultScalaModule)


  // Columns of interest are:
  // - sql_data_type
  // - name
  // - nullable
  // - default_value
  private val selected: List[String] =
    List(
      "name",
      "sql_data_type",
      "nullable",
      "default_value"
    )


  /**
    * Collect the relevant column indices from a header
    *
    * @param h
    * @return
    */
  private def columnsFromHeader(h: List[String]): Map[String, Int] =
    h
      .zipWithIndex
      .collect {
        case (s, i) if selected.contains(s) => s -> i
      }
      .toMap


  /**
    * Generate a type
    *
    * @param t
    * @param nullable
    * @param default Ignored for now
    * @return
    */
  private def typeGenerator(t: String, nullable: Int, default: Option[String] = None): TypeName = {
    val transform = SQL2ScalaMapper(t).apply
    if (nullable == 1) {
      TypeName(s"Option[$transform]")
    } else {
      TypeName(s"$transform")
    }
  }


  /**
    * Load schema from CSV
    *
    * @param filename
    * @return
    */
  def fromCsv(filename: String): CsvSchema = {
    val in = new FileInputStream(filename)

    val it: MappingIterator[List[String]] =
      csvMapper
        .readerFor(classOf[List[String]])
        .readValues(in)

    val selectors = columnsFromHeader(it.nextValue()) // process the header
    var collector = ListBuffer.empty[Field]
    while (it.hasNextValue) {
      val v = it.nextValue()
      val name = v(selectors("name"))
      val `type` = typeGenerator(v(selectors("sql_data_type")), v(selectors("nullable")).toInt)
      collector += Field.apply(name, `type`)
    }

    CsvSchema(collector)
  }

}


case class CsvSchema(fields: Seq[Field])
