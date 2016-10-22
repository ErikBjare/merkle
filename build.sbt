name := "merkle"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq("RoundEights" at "http://maven.spikemark.net/roundeights")

libraryDependencies ++= Seq("com.roundeights" %% "hasher" % "1.2.0")
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0" % "test"
