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

import geotrellis.raster._
import geotrellis.vector.Extent

object GdalReader {

  /** This function reads a single band of a GDAL-recognized raster format
    *
    * @param path The path GDAL will attempt to read at
    * @param band The band number (1 indexed) to be read
    * @return (Tile, RasterExtent) The specified band as a tile and its extent as
    *                              a RasterExtent
    */
  def read(path: String, band: Int = 1): (Tile, RasterExtent) = {
    val gdalRaster: RasterDataSet = Gdal.open(path)

    try {
      val extent = Extent(
        gdalRaster.xmin,
        gdalRaster.ymin,
        gdalRaster.xmax,
        gdalRaster.ymax
      )
      val (lcols, lrows) = (gdalRaster.cols, gdalRaster.rows)

      if(lcols * lrows > Int.MaxValue)
        sys.error(s"Cannot read this raster, cols * rows exceeds maximum array index ($lcols * $lrows)")

      val (cols, rows) = (lcols.toInt, lrows.toInt)

      val rasterExtent = RasterExtent(extent, cols, rows)

      (gdalRaster.bands(band - 1).toTile, rasterExtent)
    } finally {
      gdalRaster.close
    }
  }
}
