import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbt.Keys._

import scalariform.formatter.preferences._

name := "paypal"

version := "0.1.0"

scalaVersion := "2.11.7"

val commonScalariform = scalariformSettings :+ (ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignParameters, true)
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(PreserveSpaceBeforeArguments, true)
  .setPreference(RewriteArrowSymbols, true))

val commons = Seq(
  organization := "me.passenger",
  scalaVersion := "2.11.7",
  resolvers ++= Seq(
    "dgtl" at "http://dev.dgtl.pro/repo/",
    "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
    "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    Resolver.sonatypeRepo("snapshots")
  ),
  publishTo := Some(Resolver.file("file", new File("/mvn-repo")))
) ++ commonScalariform

commons


lazy val `psgr-paypal` = (project in file(".")).settings(commons: _*).settings(
  name := "paypal",
  version := "0.1.0",
  libraryDependencies ++= Seq(
    json % Provided,
    ws % Provided,
    cache % Provided
  )
)

offline := true

libraryDependencies ++= {
  Seq(
    json % Provided,
    ws % Provided,
    cache % Provided
  )
}

//resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += Resolver.sonatypeRepo("releases")

resolvers += Resolver.jcenterRepo

testOptions in Test += Tests.Argument("junitxml")

parallelExecution in Test := false

fork in Test := true

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}
