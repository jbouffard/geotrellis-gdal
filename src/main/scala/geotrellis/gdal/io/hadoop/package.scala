package geotrellis.gdal.io

import org.apache.spark.SparkContext

package object hadoop {
  implicit class withGdalHadoopSparkContextMethods(val sc: SparkContext)
      extends GdalHadoopSparkContextMethods
}
