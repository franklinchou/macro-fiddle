package dynamic.macros

import scala.annotation.StaticAnnotation

class FromSchema(schemaFile: String) extends StaticAnnotation {

  def macroTransform(annottees: Any*) = macro SourceGenerator.generate

}
