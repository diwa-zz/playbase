// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.0-RC2")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.2.0")
