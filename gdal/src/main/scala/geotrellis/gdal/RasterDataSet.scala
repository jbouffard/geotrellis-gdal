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
import geotrellis.raster.resample.ResampleMethod
import geotrellis.vector.Extent
import geotrellis.proj4.CRS

import org.gdal.gdal._
import org.gdal.osr.SpatialReference

import java.awt.Color
import java.nio.ByteBuffer

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.reflect.ClassTag

class RasterDataSet(val ds: Dataset) {
  lazy val driver: Driver = ds.GetDriver()
  lazy val files: Seq[String] = {
    val v = ds.GetFileList()
    if(v == null) Seq()
    else v.toSeq.map { _.asInstanceOf[String] }
  }

  private lazy val colsLong: Long = ds.getRasterXSize
  private lazy val rowsLong: Long = ds.getRasterYSize

  lazy val cols: Int = colsLong.toInt
  lazy val rows: Int = rowsLong.toInt

  lazy val extent: Extent =
    Extent(xmin, ymin, xmax, ymax)

  lazy val rasterExtent: RasterExtent = {
    if(colsLong * rowsLong > Int.MaxValue)
      sys.error(s"Cannot read this raster, cols * rows exceeds maximum array index ($cols * $rows)")

    RasterExtent(extent, cols.toInt, rows.toInt)
  }

  lazy val gridBounds: GridBounds =
    GridBounds(0, 0, cols - 1, rows - 1)

  lazy val xmin: Double =
    geoTransform(0)

  lazy val ymin: Double =
    geoTransform(3) + geoTransform(5) * rows

  lazy val xmax: Double =
    geoTransform(0) +  geoTransform(1) * cols

  lazy val ymax: Double =
    geoTransform(3)

  lazy val cellSize: CellSize =
    CellSize(geoTransform(1), math.abs(geoTransform(5)))

  lazy val projection: Option[String] = {
    val proj = ds.GetProjectionRef
    if(proj == null || proj.isEmpty) None
    else Some(proj)
  }

  lazy val crs: Option[CRS] =
    projection map { projection =>
      val srs = new SpatialReference(projection)
      CRS.fromString(srs.ExportToProj4())
    }

  lazy val geoTransform: Array[Double] =
    ds.GetGeoTransform

  lazy val groundControlPointCount: Long =
    ds.GetGCPCount

  lazy val groundControlPoints: Seq[GroundControlPoint] = {
    val gcps = new java.util.Vector[GCP]()
    ds.GetGCPs(gcps)
    gcps.map(GroundControlPoint(_)).toSeq
  }

  def metadata: List[String] =
    ds.GetMetadata_List("").toList.map(_.asInstanceOf[String])

  def metadata(id: String): List[String] =
    ds.GetMetadata_List(id).toList.map(_.asInstanceOf[String])

  def band(i: Int): RasterBand = new RasterBand(ds.GetRasterBand(i), cols.toInt, rows.toInt)

  lazy val noDataValue: Option[Double] = {
    val band = ds.GetRasterBand(1)
    val arr = Array.ofDim[java.lang.Double](1)
    band.GetNoDataValue(arr)

    arr.headOption.flatMap(Option(_)).map(_.doubleValue)
  }

  def resample(
    cellSize: CellSize,
    resampleMethod: ResampleMethod
  ): RasterDataSet =
    resample(List("-tap", "-tr", s"${cellSize.width}", s"${cellSize.height}"), resampleMethod)

  def resample(
    tileDimensions: (Int, Int),
    resampleMethod: ResampleMethod
  ): RasterDataSet =
    resample(List("-ts", s"${tileDimensions._1}", s"${tileDimensions._2}"), resampleMethod)

  private def resample(
    targetParams: List[String],
    resampleMethod: ResampleMethod
  ): RasterDataSet = {
    val optionsList =
      List(
        "-of", "VRT",
        "-r", s"${Gdal.deriveResampleMethodString(resampleMethod)}"
      ) ::: targetParams

    val warpOptions = new WarpOptions(new java.util.Vector(optionsList.asJava))

    new RasterDataSet(gdal.Warp("", Array(ds), warpOptions))
  }

  lazy val bandCount: Int = ds.getRasterCount

  lazy val bands: Vector[RasterBand] =
    (1 to bandCount)
      .map(band(_))
      .toVector

  def close(): Unit = ds.delete
}

class RasterColor(color: Color) {
  override
  def toString: String = s"${color.getRed},${color.getGreen},${color.getBlue},${color.getAlpha}"
}
