name := "geotrellis-spark-gdal-etl"

libraryDependencies ++= Seq(
  Dependencies.geotrellisSparkEtl % Provided,
  Dependencies.sparkCore % Provided,
  Dependencies.hadoopClient % Provided
)
