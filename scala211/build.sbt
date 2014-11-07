import aether.Aether._
import org.sbtidea.SbtIdeaPlugin._
import scoverage.ScoverageSbtPlugin
import scalariform.formatter.preferences._

val VERSION = "0.1.0"

val SCALA_VERSION = "2.11.2"

val CROSS_VERSION = Seq("2.10.4", "2.11.2")

val ORGANIZATION = "jp.seraphr"

val TEST_PHASE = "test->test"
val COMPILE_PHASE = "compile->compile"
val TEST_AND_COMPILE_PHASE = List(TEST_PHASE, COMPILE_PHASE).mkString(";")

val COMMON_DEPENDENCIES = Seq(
  "org.scalatest" %% "scalatest" % "2.2.0" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.5" % "test"
)

val RIFORM_SETTINGS = scalariformSettings ++ (
  ScalariformKeys.preferences := (ScalariformKeys.preferences.value
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 60))
    .setPreference(DoubleIndentClassDeclaration, false))

val COMMON_SETTINGS = Seq(
  organization := ORGANIZATION,
  version := VERSION,
  scalaVersion := SCALA_VERSION,
  crossScalaVersions := CROSS_VERSION,
  EclipseKeys.withSource := true,
  EclipseKeys.withJavadoc := true,
  EclipseKeys.eclipseOutput := Some(".eclipseTarget"),
  ideaExcludeFolders := Seq(".idea", ".idea_modules"),
  testOptions in ScoverageTest := Seq(
    Tests.Argument("-oS", "-u", "target/junit")
  ),
  testOptions in Test := Seq(
    Tests.Argument("-oS", "-u", "target/junit")
  ),
  EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource,
  scalacOptions in (Compile, doc) ++= Seq("-groups", "-implicits", "-diagrams"),
  scalacOptions ++= Seq("-encoding", "UTF-8", "-feature", "-deprecation", "-Xlint"),
  javacOptions ++= Seq("-encoding", "UTF-8"),
  incOptions := incOptions.value.withNameHashing(true)
) ++ ScoverageSbtPlugin.instrumentSettings ++ net.virtualvoid.sbt.graph.Plugin.graphSettings ++ RIFORM_SETTINGS

lazy val merger = Project(
  id = "sandbox-scala211",
  base = file("."),
  settings = Defaults.defaultSettings ++ COMMON_SETTINGS ++ Seq(
    name := "sandbox-scala211",
    libraryDependencies ++= COMMON_DEPENDENCIES ++ Seq(
    )
  )
)
