name := "macro-fiddle"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.0",
  "com.fasterxml.jackson.module" % "jackson-module-scala" % "2.0.2"

)