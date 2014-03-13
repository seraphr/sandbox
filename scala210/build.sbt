packSettings

name := "sandbox-scala210"

version := "0.0.1"

scalaVersion := "2.10.2"

organization := "kurusugawa"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"

EclipseKeys.withSource := true

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

packMain := Map("stacklessCont" -> "jp.seraphr.sandbox.continuation.StacklessContMain")