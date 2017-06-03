package geotrellis.gdal.io.hadoop

import geotrellis.raster.Tile
import geotrellis.spark._
import geotrellis.spark.etl.config.EtlConf
import geotrellis.spark.etl.hadoop.{HadoopInput, getPath}
import org.apache.hadoop.fs.Path
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD


class NetCdfHadoopInput extends HadoopInput[TemporalProjectedExtent, Tile] {
  val format = "netcdf"
  def apply(conf: EtlConf)(implicit sc: SparkContext): RDD[(TemporalProjectedExtent, Tile)] =
    sc.netCdfRDD(new Path(getPath(conf.input.backend).path))
}
