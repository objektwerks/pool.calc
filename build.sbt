name := "pool.balance"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.1.3-RC4"
mainClass := Some("pool.App")
libraryDependencies ++= {
  Seq(
    "org.scalafx" %% "scalafx" % "18.0.1-R27",
    "org.jfxtras" % "jfxtras-controls" % "17-r1",
    "org.scalikejdbc" %% "scalikejdbc" % "4.0.0",
    "com.zaxxer" % "HikariCP" % "5.0.1" exclude("org.slf4j", "slf4j-api"),
    "com.h2database" % "h2" % "2.1.212",
    "com.typesafe" % "config" % "1.4.2",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.2.11",
    "org.scalatest" %% "scalatest" % "3.2.12" % Test
  )
}

/*
lazy val createBuildDir = taskKey[File]("Create build dir.")
createBuildDir := {
  val buildDir = baseDirectory.value / "build"
  println(s"build dir: ${buildDir}")
  IO.createDirectory(buildDir)
  buildDir
}
// assembly / assemblyOutputPath := createBuildDir.value

lazy val copyAssemblyJar = taskKey[Unit]("Copy assembly jar to build dir.")
copyAssemblyJar := {
  import java.nio.file.Files
  import java.nio.file.Path
  import java.nio.file.Paths
  import java.nio.file.StandardCopyOption

  val jar = (assembly / assemblyOutputPath).value
  val source: Path = Paths.get(jar.toString)
  println(s"source: ${source.toString}")

  val buildDir = baseDirectory.value / "build"
  val target: Path = (buildDir.toPath
  println(s"target: ${target.toString}")
  println(s"Does build dir exist: ${Files.exists(target)}")

  assert(Files.isDirectory(target), s"$target is not a directory!")

  Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING)
}
*/

/*
See assembly section in readme.
1. sbt -Dtarget="mac" clean test assembly
2. sbt -Dtarget="m1" clean test assembly
3. sbt -Dtarget="win" clean test assembly
4. sbt -Dtarget="linux" clean test assembly
*/
lazy val os: String = sys.props.getOrElse("target", "") match {
  case name if name.startsWith("mac")   => "mac"
  case name if name.startsWith("m1")    => "mac-aarch64"
  case name if name.startsWith("win")   => "win"
  case name if name.startsWith("linux") => "linux"
  case _ => ""
}

if (os == "mac") assemblyJarName := "pool-balance-mac-0.1.jar"
else if (os == "mac-aarch64") assemblyJarName := "pool-balance-m1-0.1.jar"
else if (os == "win") assemblyJarName := "pool-balance-win-0.1.jar"
else if (os == "linux") assemblyJarName := "pool-balance-linux-0.1.jar"
else assemblyJarName := "pool-balance-no-valid-target-specified-0.1.jar"

lazy val javafxModules = Seq("base", "controls", "web")
libraryDependencies ++= javafxModules.map( module =>
  "org.openjfx" % s"javafx-$module" % "18.0.1" classifier os
)

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}