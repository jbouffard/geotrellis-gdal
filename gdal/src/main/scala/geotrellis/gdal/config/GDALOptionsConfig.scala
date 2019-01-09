package geotrellis.gdal.config

import org.gdal.gdal.gdal

case class GDALOptionsConfig(maxDatasetPoolSize: Int = 1, vrtSharedSource: Boolean = false, cplDebug: String = "OFF") {
  def setMaxDatasetPoolSize: Unit = gdal.SetConfigOption("GDAL_MAX_DATASET_POOL_SIZE", s"$maxDatasetPoolSize")
  def setVRTSharedSource: Unit = gdal.SetConfigOption("VRT_SHARED_SOURCE", s"${ if(vrtSharedSource) 1 else 0 }")
  def setCPLDebugMode: Unit = gdal.SetConfigOption("CPL_DEBUG", cplDebug)
  def set: Unit = { setMaxDatasetPoolSize; setVRTSharedSource; setCPLDebugMode }
}

object GDALOptionsConfig extends PureConfigSettings {
  lazy val conf: GDALOptionsConfig = pureconfig.loadConfigOrThrow[GDALOptionsConfig]("gdal.options")
  implicit def gdalOptionsConfig(obj: GDALOptionsConfig.type): GDALOptionsConfig = conf
}
