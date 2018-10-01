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
import geotrellis.proj4._
import geotrellis.vector.Extent
import geotrellis.proj4.CRS

import org.gdal.gdal.gdal
import org.gdal.osr.SpatialReference

import java.awt.Color
import java.nio.ByteBuffer


case class RasterDataset(uri: String) {
  @transient private lazy val dataset = Gdal.open(uri)

  private lazy val geoTransform: Array[Double] = dataset.GetGeoTransform

  lazy val bandCount: Int = dataset.getRasterCount

  private lazy val colsLong: Long = dataset.getRasterXSize
  private lazy val rowsLong: Long = dataset.getRasterYSize

  lazy val cols: Int = colsLong.toInt
  lazy val rows: Int = rowsLong.toInt

  lazy val gridBounds: GridBounds = GridBounds(0, 0, cols, rows)

  lazy val crs: CRS = {
    val projection: Option[String] = {
      val proj = dataset.GetProjectionRef

      if (proj == null || proj.isEmpty) None
      else Some(proj)
    }

    projection.map { proj =>
      val srs = new SpatialReference(proj)
      CRS.fromString(srs.ExportToProj4())
    }.getOrElse(CRS.fromEpsgCode(4326))
  }

  private lazy val reader: GdalReader = GdalReader(dataset)

  lazy val cellType: CellType = {
    val (noDataValue, bufferType, typeSizeInBits) = {
      val baseBand = dataset.GetRasterBand(1)

      val arr = Array.ofDim[java.lang.Double](1)
      baseBand.GetNoDataValue(arr)

      val nd = arr.headOption.flatMap(Option(_)).map(_.doubleValue())
      val bufferType = baseBand.getDataType
      val typeSizeInBits = gdal.GetDataTypeSize(bufferType)
      (nd, bufferType, Some(typeSizeInBits))
    }
    Gdal.deriveGTCellType(bufferType, noDataValue, typeSizeInBits)
  }

  lazy val extent: Extent = {
    val xmin: Double = geoTransform(0)
    val ymin: Double = geoTransform(3) + geoTransform(5) * rows
    val xmax: Double = geoTransform(0) + geoTransform(1) * cols
    val ymax: Double = geoTransform(3)

    Extent(xmin, ymin, xmax, ymax)
  }

  def rasterExtent: RasterExtent =
    RasterExtent(
      extent,
      geoTransform(1),
      math.abs(geoTransform(5)),
      cols,
      rows
    )

  def readBounds(bounds: Traversable[GridBounds], bands: Seq[Int]): Iterator[Raster[MultibandTile]] = {
    val tuples =
      bounds.map { gb =>
        val re = rasterExtent.rasterExtentFor(gb)
        val boundsClamped = rasterExtent.gridBoundsFor(re.extent, clamp = true)
        val bounds = rasterExtent.gridBoundsFor(re.extent, clamp = false)
        (bounds, boundsClamped, re)
      }

    tuples.map { case (gb, gbc, re) =>
      val initialTile = reader.read(gbc)

      val (gridBounds, tile) =
        if (initialTile.cols != re.cols || initialTile.rows != re.rows) {
          val updatedTiles = initialTile.bands.map { band =>
            // TODO: it can't be larger than the source is, fix it
            val protoTile = band.prototype(math.min(re.cols, cols), math.min(re.rows, rows))

            protoTile.update(gb.colMin - gbc.colMin, gb.rowMin - gbc.rowMin, band)
            protoTile
          }

          (gb, MultibandTile(updatedTiles))
        } else (gbc, initialTile)

      Raster(tile, rasterExtent.extentFor(gridBounds))
    }.toIterator
  }

  def read(extent: Extent, bands: Seq[Int]): Option[Raster[MultibandTile]] = {
    val bounds = rasterExtent.gridBoundsFor(extent, clamp = false)
    read(bounds, bands)
  }

  def read(bounds: GridBounds, bands: Seq[Int]): Option[Raster[MultibandTile]] = {
    val it = readBounds(List(bounds).flatMap(_.intersection(gridBounds)), bands)
    if (it.hasNext) Some(it.next) else None
  }

  def readExtents(extents: Traversable[Extent]): Iterator[Raster[MultibandTile]] = {
    // TODO: clamp = true when we have PaddedTile ?
    val bounds = extents.map(rasterExtent.gridBoundsFor(_, clamp = false))
    readBounds(bounds, Nil)
  }

  def read(extent: Extent): Option[Raster[MultibandTile]] =
      read(extent, (0 until bandCount))

  def read(bounds: GridBounds): Option[Raster[MultibandTile]] =
      read(bounds, (0 until bandCount))

  def readExtents(extents: Traversable[Extent], bands: Seq[Int]): Iterator[Raster[MultibandTile]] =
      extents.toIterator.flatMap(read(_, bands).toIterator)

  def readBounds(bounds: Traversable[GridBounds]): Iterator[Raster[MultibandTile]] =
    bounds.toIterator.flatMap(read(_, (0 until bandCount)).toIterator)
}

class RasterColor(color: Color) {
  override
  def toString: String = s"${color.getRed},${color.getGreen},${color.getBlue},${color.getAlpha}"
}
