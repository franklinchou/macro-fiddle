package dynamic.schema

case class TypeName(fullName: String) {

  private def lastDot: Int = fullName.lastIndexOf('.')

  def packageName: String = fullName.take(lastDot)

  def shortName: String = fullName.drop(lastDot + 1)

}