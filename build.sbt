enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

name := "Games"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "com.lihaoyi" %%% "scalatags" % "0.6.2",
  "com.lihaoyi" %%% "scalarx" % "0.2.8",
  "com.lihaoyi" %% "ammonite-ops" % "0.2.4",
  "org.scala-lang.modules" %% "scala-async" % "0.9.2"
)
