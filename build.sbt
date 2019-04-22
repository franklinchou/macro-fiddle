name := "macro-fiddle"

version := "0.1"

scalaVersion := "2.12.8"

// For use with macro expansion
lazy val paradisePlugin = "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full

lazy val generators =
  project
    .in(file("macro"))
    .settings(
      libraryDependencies ++= Seq(
        "org.scala-lang" % "scala-reflect" % scalaVersion.value,
        // "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.6",
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.6",
        "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
      ),
      addCompilerPlugin(paradisePlugin)
    )

lazy val driver =
  project
    .in(file("driver"))
    .dependsOn(generators)
    .settings(
      addCompilerPlugin(paradisePlugin)
    )

lazy val root =
  project
    .in(file("."))
    .settings(
      addCompilerPlugin(paradisePlugin)
    )
    .aggregate(generators, driver)
