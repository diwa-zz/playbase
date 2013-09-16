import sbt._
import Keys._
import play.Project._

object Version {
  val appName         = "playbase"
  val appVersion      = "0.1-SNAPSHOT"
}

object Options {
  val scalaBuildOptions = Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-Ywarn-all", // doesn't actually turn them all on :-\
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-dead-code", // confused by ???, sadly
  "-Ywarn-inaccessible",
  "-Ywarn-nullary-override",
  " -Ywarn-nullary-unit",
  "-Xlint"
)
  val viewAutoImports = Seq("com.hirepro.assesspro.common.business.domain.models._")
}

object Library {
  val mySQL               = "mysql" % "mysql-connector-java" % "5.1.26"
  val slickPlay           = "com.typesafe.play" %% "play-slick" % "0.5.0.2-SNAPSHOT"
  val guiceScala          = "net.codingwell" %% "scala-guice" % "4.0.0-beta"
  val selenium            = "org.seleniumhq.selenium" % "selenium-java" % "2.35.0"
  val mockito             = "org.mockito" % "mockito-core" % "1.9.5"
  val pegDown             = "org.pegdown" % "pegdown" % "1.4.1"
  val classCycle          = "org.specs2" % "classycle" % "1.4.1"
}

object Dependencies {
  import Library._
  val fwkDependencies = Seq(
    jdbc,
    mySQL,
    slickPlay,
    guiceScala,
    selenium    % "test",
    mockito     % "test",
    pegDown     % "test",
    classCycle  % "test"
  )
}
