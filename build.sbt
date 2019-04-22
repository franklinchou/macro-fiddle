name := "macro-fiddle"

version := "0.1"

scalaVersion := "2.12.8"

lazy val generators =
  project
    .in(file("macro"))
    .settings(
      libraryDependencies ++= Seq(
        "org.scala-lang" % "scala-reflect" % scalaVersion.value,
        // "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.6",
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.6"
      )
    )

lazy val driver =
  project
    .in(file("driver"))
    .dependsOn(generators)

lazy val root = project.in(file(".")).aggregate(generators, driver)
