resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.11")

addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "4.1.0")

addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.0.3")