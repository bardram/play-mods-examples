name := "avro-example"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.7"

lazy val root = project in file(".") enablePlugins(PlayJava)

libraryDependencies += "me.tfeng.play-mods" % "avro" % "0.9.8"

Avro.settings
