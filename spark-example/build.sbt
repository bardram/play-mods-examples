name := "spark-example"

version := "1.0.0-SNAPSHOT"

lazy val root = project in file(".") enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  "me.tfeng.play-mods" % "spring" % "0.5.1-SNAPSHOT",
  "me.tfeng.toolbox" % "kafka" % "0.5.1-SNAPSHOT",
  "org.apache.spark" % "spark-streaming_2.10" % "1.3.1",
  "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.3.1",
  "org.hamcrest" % "hamcrest-all" % "1.3" % "test",
  javaWs % "test"
)

routesGenerator := InjectedRoutesGenerator
