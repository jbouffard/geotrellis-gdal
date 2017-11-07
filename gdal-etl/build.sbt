name := "geotrellis-spark-gdal-etl"

libraryDependencies ++= Seq(
  Dependencies.geotrellisSpark % Provided,
  Dependencies.geotrellisSparkEtl % Provided,
  Dependencies.sparkCore % Provided,
  Dependencies.hadoopClient % Provided
)
