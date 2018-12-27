/*
 * Copyright 2018 Azavea
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

import geotrellis.gdal.{GDAL, GDALDataset}
import geotrellis.gdal.cache.LazyCache

import com.github.blemale.scaffeine.Scaffeine
import com.github.benmanes.caffeine.cache.RemovalCause.EXPLICIT
import com.typesafe.scalalogging.LazyLogging

case class GDALCacheConfig(
  maximumSize: Option[Long] = None,
  enableDefaultRemovalListener: Boolean = true,
  valuesType: ValueType = Weak,
  enabled: Boolean = true,
  withShutdownHook: Boolean = true
) extends LazyLogging {
  def getCache: LazyCache[String, GDALDataset] = {
    def get = () => {
      val cache = Scaffeine()
      maximumSize.foreach(cache.maximumSize)
      valuesType match {
        case Weak => cache.weakValues()
        case Soft => cache.softValues()
        case _ => // do nothing
      }
      if (enableDefaultRemovalListener)
        cache.removalListener[String, GDALDataset] { case (key, dataset, event) =>
          logger.debug(s"removalListener: ${key}-${dataset} event: $event")
          logger.debug(s"removalListener::dataset != null: ${dataset != null}")

          if (dataset != null) {
            logger.debug(s"removalListener::dataset.underlying != null: ${dataset.underlying != null}")
            event match {
              case EXPLICIT => dataset.delete
              case _ =>
                if (dataset.getChildReference.isEmpty) {
                  // if it's a cache on weak refs, lets remove all parents from the cache on hard refs
                  if (valuesType.isWeak) {
                    val parents = dataset.getParentReferences
                    if (parents != null) parents.foreach { case (k, _) => GDAL.cacheOrdering.invalidate(k) }
                  }
                  dataset.delete
                }/* else {
                  GDAL.cache.get(key, _ => dataset)
                }*/
            }
          }
        }

      cache.build[String, GDALDataset]
    }

    LazyCache(get, enabled)
  }

  def getWeakOrderingCache: LazyCache[String, GDALDataset] = {
    def get = () => {
      val cache = Scaffeine()
      maximumSize.foreach(cache.maximumSize)
      if (enableDefaultRemovalListener)
        cache.removalListener[String, GDALDataset] { case (key, dataset, event) =>
          logger.debug(s"WeakOrderingCache::removalListener: ${dataset} event: $event")
          dataset.deleteChildReference
          // we don't free mem on this step
          // if (dataset != null) dataset.delete
        }

      cache.build[String, GDALDataset]
    }

    LazyCache(get, enabled && valuesType.isWeak)
  }

  def addShutdownHook: Unit =
    if(withShutdownHook) Runtime.getRuntime.addShutdownHook(new Thread() { override def run(): Unit = GDAL.cacheCleanUp })
}

object GDALCacheConfig extends PureConfigSettings {
  lazy val conf: GDALCacheConfig = pureconfig.loadConfigOrThrow[GDALCacheConfig]("gdal.cache")
  implicit def gdalCacheConfig(obj: GDALCacheConfig.type): GDALCacheConfig = conf
}
