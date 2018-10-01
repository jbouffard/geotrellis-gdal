import sbt._

lazy val commonSettings = Seq(
  version := Version.geotrellisGdal,
  scalaVersion := Version.scala,
  description := "GeoTrellis GDAL Bindings",
  organization := "com.azavea.geotrellis",
  name := "geotrellis-gdal",
  licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),
  headerLicense := Some(HeaderLicense.ALv2("2017", "Azavea")),
  scalacOptions ++= Seq(
    "-deprecation",
    "-unchecked",
    "-feature",
    "-language:implicitConversions",
    "-language:reflectiveCalls",
    "-language:higherKinds",
    "-language:postfixOps",
    "-language:existentials",
    "-language:experimental.macros",
    "-feature"
  ),
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  shellPrompt := { s => Project.extract(s).currentProject.id + " > " },
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
    </developers>
  ),
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots"),
    "locationtech-releases" at "https://repo.locationtech.org/content/groups/releases",
    "locationtech-snapshots" at "https://repo.locationtech.org/content/groups/snapshots",
    "boundless repo" at "https://repo.boundlessgeo.com/main/",
    "geosolutions" at "http://maven.geo-solutions.it/",
    "osgeo" at "http://download.osgeo.org/webdev/geotools/"
  ),
  fork in Test := true,
  parallelExecution in Test := false,
  javaOptions ++= Seq("-Xmx1024m", "-Xmx6144m", "-Djava.library.path=/usr/local/lib")
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .aggregate(gdal, `gdal-etl`)

lazy val `gdal-etl` = project
  .settings(commonSettings: _*)
  .settings(name := "geotrellis-spark-gdal-etl")
  .dependsOn(`gdal`)

lazy val `gdal` = project
  .settings(commonSettings: _*)
  .settings(name := "geotrellis-gdal")
