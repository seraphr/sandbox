val dottyVersion = "0.11.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-simple",
    version := "0.1.0",

    scalaVersion := dottyVersion,
    scalacOptions += "-Ykind-polymorphism",

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
