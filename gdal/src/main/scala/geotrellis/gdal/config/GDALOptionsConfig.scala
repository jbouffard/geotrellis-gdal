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

import scala.collection.concurrent.TrieMap

case class GDALOptionsConfig(options: Map[String, String] = Map(), useExceptions: Boolean = true) {
  def setUseExceptions: Unit = if(useExceptions) gdal.UseExceptions()
  def set: Unit = {
    // register first config options from the conf file
    options.foreach { case (key, value) => gdal.SetConfigOption(key, value) }
    // register programmatically set options
    GDALOptionsConfig.setRegistryOptions
    // set exceptions handling mode
    setUseExceptions
  }
}

object GDALOptionsConfig extends PureConfigSettings with Serializable {
  private val optionsRegistry = TrieMap[String, String]()

  def registerOption(key: String, value: String): Unit = optionsRegistry += (key -> value)
  def registerOptions(seq: (String, String)*): Unit = seq.foreach(optionsRegistry += _)
  def setRegistryOptions: Unit = optionsRegistry.foreach { case (key, value) => gdal.SetConfigOption(key, value) }

  lazy val conf: GDALOptionsConfig = pureconfig.loadConfigOrThrow[GDALOptionsConfig]("gdal.settings")
  implicit def gdalOptionsConfig(obj: GDALOptionsConfig.type): GDALOptionsConfig = conf
}
