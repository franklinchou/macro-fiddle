package com.peeriq.macros

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.language.experimental.macros

@compileTimeOnly("Enable macro paradise to expand macro annotations")
class FromSchema(schemaFile: String) extends StaticAnnotation {

  def macroTransform(annottees: Any*) = macro SourceGenerator.generate

}
