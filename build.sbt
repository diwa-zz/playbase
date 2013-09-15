import play.Project._
import Dependencies._
import com.typesafe.sbt.SbtScalariform._
import scalariform.formatter.preferences._

name := "playbase"

version := "1.0-SNAPSHOT"

scalacOptions ++= Options.scalaBuildOptions

libraryDependencies ++= fwkDependencies

playScalaSettings


defaultScalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
.setPreference(AlignParameters, true)
.setPreference(AlignSingleLineCaseStatements, true)
.setPreference(DoubleIndentClassDeclaration, true)
.setPreference(PreserveDanglingCloseParenthesis, true)

addCommandAlias("t", "test")

addCommandAlias("to", "test-only")

addCommandAlias("tq", "testQuick")

addCommandAlias("c", "compile")

addCommandAlias("tc", "test:compile")

addCommandAlias("cct", ";clean ;compile ;test")

addCommandAlias("r", "run")

addCommandAlias("f", ";scalariformFormat ;test:scalariformFormat")

addCommandAlias("u", "update")

addCommandAlias("r", "reload")

addCommandAlias("d", "dependencies")

addCommandAlias("p", "project")

addCommandAlias("q", "quit")
