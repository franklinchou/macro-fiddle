package dynamic.macros

import dynamic.schema.TypeSchema

import scala.language.experimental.macros
import scala.reflect.macros.blackbox


object SourceGenerator {

  def generate(c: blackbox.Context)(annottees: c.Expr[Any]*) = {

    import c.universe._

    // retrieve the schema path
    val schemaPath = c.prefix.tree match {
      case Apply(_, List(Literal(Constant(x)))) => x.toString
      case _ => c.abort(c.enclosingPosition, "schema file path not specified")
    }

    // retrieve the annotated class name
    val className = annottees.map(_.tree) match {
      case List(q"class $name") => name
      case _ => c.abort(c.enclosingPosition, "the annotation can only be used with classes")
    }

    // load the schema from JSON
    val schema = TypeSchema.fromJson(schemaPath)

    // produce the list of constructor params
    val params = schema.fields.map { f =>
      val fieldName = TermName(f.name)
      val fieldType = TermName(f.`type`.fullName)
      q"val $fieldName: $fieldType"
    }

    val json = TypeSchema.toJson(schema)

    // output case class definition
    c.Expr(
      q"""
         case class $className(..$params) {
           def schema = $json
         }
       """
    )

  }

}