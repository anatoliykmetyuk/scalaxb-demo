import scala.sys.process._ 

val ScalaVer = "2.12.7"

val CatsEffect    = "1.0.0"
val KindProjector = "0.9.8"

lazy val commonSettings = Seq(
  name    := "sxbtest"
, version := "0.1.0"
, scalaVersion := ScalaVer
, libraryDependencies ++= Seq(
    "org.typelevel"  %% "cats-effect" % CatsEffect
  , "org.scala-lang.modules" %% "scala-xml" % "1.1.1"
  , "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.1"
  )

, addCompilerPlugin("org.spire-math" %% "kind-projector" % KindProjector)
, scalacOptions ++= Seq(
      "-deprecation"
    , "-encoding", "UTF-8"
    , "-feature"
    , "-language:existentials"
    , "-language:higherKinds"
    , "-language:implicitConversions"
    , "-language:experimental.macros"
    , "-unchecked"
    // , "-Xfatal-warnings"
    // , "-Xlint"
    // , "-Yinline-warnings"
    , "-Ywarn-dead-code"
    , "-Xfuture"
    , "-Ypartial-unification")
)

lazy val generateSources = taskKey[Unit]("Generate sources")

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    initialCommands := "import sxbtest._, Main._"
  )
