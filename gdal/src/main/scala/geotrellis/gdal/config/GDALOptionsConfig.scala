package geotrellis.gdal.config

import geotrellis.gdal.sgdal

case class GDALOptionsConfig(maxDatasetPoolSize: Int = 1, vrtSharedSource: Boolean = false) {
  def setMaxDatasetPoolSize: Unit = sgdal.setConfigOption("GDAL_MAX_DATASET_POOL_SIZE", s"$maxDatasetPoolSize")
  def setVRTSharedSource: Unit = sgdal.setConfigOption("VRT_SHARED_SOURCE", s"${ if(vrtSharedSource) 1 else 0 }")
  def set: Unit = { setMaxDatasetPoolSize; setVRTSharedSource }
}

object GDALOptionsConfig extends PureConfigSettings {
  lazy val conf: GDALOptionsConfig = pureconfig.loadConfigOrThrow[GDALOptionsConfig]("gdal.options")
  implicit def gdalOptionsConfig(obj: GDALOptionsConfig.type): GDALOptionsConfig = conf
}
