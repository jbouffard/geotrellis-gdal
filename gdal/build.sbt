name := "geotrellis-gdal"

libraryDependencies ++= Seq(
  Dependencies.gdal,
  Dependencies.geotrellisRaster % Provided,
  Dependencies.geotrellisSpark % Provided,
  Dependencies.geotrellisSparkTestkit % Test,
  Dependencies.sparkCore % Provided,
  Dependencies.hadoopClient % Provided,
  Dependencies.scalaTest % Test,
  Dependencies.scopt
)
