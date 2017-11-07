/*
 * Copyright 2017 Azavea
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

package geotrellis.gdal

import org.gdal.gdal.gdal
import org.gdal.gdal.Dataset
import org.gdal.gdalconst.gdalconstConstants

class GdalException(code: Int, msg: String) 
    extends RuntimeException(s"GDAL ERROR $code: $msg")

object GdalException {
  def lastError(): GdalException =
    new GdalException(gdal.GetLastErrorNo,
                      gdal.GetLastErrorMsg)
}

object Gdal {
  gdal.AllRegister()

  def open(path: String): RasterDataSet = {
    val ds = gdal.Open(path, gdalconstConstants.GA_ReadOnly)
    if(ds == null) {
      throw GdalException.lastError()
    }
    new RasterDataSet(ds)
  }
}
