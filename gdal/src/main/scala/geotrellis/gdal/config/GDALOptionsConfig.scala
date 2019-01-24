/*
 * Copyright 2019 Azavea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package geotrellis.gdal.config

import org.gdal.gdal.gdal

case class GDALOptionsConfig(maxDatasetPoolSize: Int = 100, vrtSharedSource: Boolean = false, cplDebug: String = "OFF", useExceptions: Boolean = true) {
  def setMaxDatasetPoolSize: Unit = gdal.SetConfigOption("GDAL_MAX_DATASET_POOL_SIZE", s"$maxDatasetPoolSize")
  def setVRTSharedSource: Unit = gdal.SetConfigOption("VRT_SHARED_SOURCE", s"${ if(vrtSharedSource) 1 else 0 }")
  def setCPLDebugMode: Unit = gdal.SetConfigOption("CPL_DEBUG", cplDebug)
  def setUseExceptions: Unit = if(useExceptions) gdal.UseExceptions()
  def set: Unit = { setMaxDatasetPoolSize; setVRTSharedSource; setCPLDebugMode; setUseExceptions }
}

object GDALOptionsConfig extends PureConfigSettings {
  lazy val conf: GDALOptionsConfig = pureconfig.loadConfigOrThrow[GDALOptionsConfig]("gdal.options")
  implicit def gdalOptionsConfig(obj: GDALOptionsConfig.type): GDALOptionsConfig = conf
}
