import sbt._

version := Version.geotrellisGdal
scalaVersion := Version.scala
description := "GeoTrellis GDAL Bindings"
organization := "com.azavea.geotrellis"
name := "geotrellis-gdal"
licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))
scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature"
)
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@github.com:geotrellis/geotrellis-gdal.git</url>
    <connection>scm:git:git@github.com:geotrellis/geotrellis-gdal.git</connection>
  </scm>
  <developers>
    <developer>
      <id>echeipesh</id>
      <name>Eugene Cheipesh</name>
      <url>http://github.com/echeipesh/</url>
    </developer>
    <developer>
      <id>lossyrob</id>
      <name>Rob Emanuele</name>
      <url>http://github.com/lossyrob/</url>
    </developer>
    <developer>
      <id>hjaekel</id>
      <name>Holger Jaekel</name>
      <url>http://github.com/hjaekel/</url>
    </developer>
    <developer>
      <id>schBen</id>
      <name>Benjamin Schmeichel</name>
      <url>http://github.com/schben/</url>
    </developer>
  </developers>)

libraryDependencies ++= Seq(
  "org.gdal"                    %   "gdal"                % Version.gdal,
  "org.locationtech.geotrellis" %% "geotrellis-raster"    % Version.geotrellis % Provided,
  "org.locationtech.geotrellis" %% "geotrellis-spark"     % Version.geotrellis % Provided,
  "org.locationtech.geotrellis" %% "geotrellis-spark-etl" % Version.geotrellis % Provided,
  "org.apache.spark"            %% "spark-core"           % Version.spark  % Provided,
  "org.apache.hadoop"           %  "hadoop-client"        % Version.hadoop % Provided,
  "org.scalatest"               %% "scalatest"            % "2.2.0"       % "test",
  "com.github.nscala-time"      %% "nscala-time"          % "2.16.0",
  "com.github.scopt"            %% "scopt"                % "3.5.0"
)

fork in Test := true
parallelExecution in Test := false

javaOptions in Test += s"-Djava.library.path=${Environment.javaLibraryPath}"
