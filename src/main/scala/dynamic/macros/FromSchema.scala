package dynamic.macros

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros

class FromSchema(schemaFile: String) extends StaticAnnotation {

  def macroTransform(annottees: Any*) = macro SourceGenerator.generate

}
