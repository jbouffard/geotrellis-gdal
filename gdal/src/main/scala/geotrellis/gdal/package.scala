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

package geotrellis

import java.util.Base64

import geotrellis.proj4.CRS
import geotrellis.raster.{CellSize, GridBounds, RasterExtent}
import geotrellis.vector.Extent
import org.gdal.gdal.{Band, Dataset}
import org.gdal.osr.SpatialReference

package object gdal extends Serializable {
  implicit class StringMethods(val str: String) extends AnyVal {
    def base64: String = Base64.getEncoder.encodeToString(str.getBytes)
  }

  implicit class GDALWarpOptionsListMethods(val options: List[GDALWarpOptions]) {
    val name: String = options.map(_.name).mkString("_")
  }

  implicit class GDALWarpOptionsListDependentMethods(options: (GDALWarpOptions, List[GDALWarpOptions])) {
    val name: String = { val (opt, list) = options; s"${opt.name}${list.name}" }
  }

  implicit class GDALWarpOptionsListDatasetDependentMethods(options: (String, List[GDALWarpOptions])) {
    val name: String = { val (path, list) = options; s"${path}${list.name}" }
  }

  implicit class GDALWarpOptionOptionsListDatasetDependentMethods(options: Option[(String, List[GDALWarpOptions])]) {
    val name: String = options.map(_.name).getOrElse("")
  }

  implicit class GDALSpatialReference(self: SpatialReference) {
    /** GetGeoTransform, OSR objects and all related to it methods are not threadsafe */
    def toCRS: CRS = AnyRef.synchronized(CRS.fromString(self.ExportToProj4))
  }

  implicit class GDALRasterBandMethods(self: Band) {
    def getNoDataValue: Option[Double] = {
      val arr = Array.ofDim[java.lang.Double](1)
      self.GetNoDataValue(arr)
      arr.headOption.flatMap(Option(_)).map(_.doubleValue())
    }
  }

  implicit class GDALDatasetMethods(self: Dataset) {
    def getRasterBands: List[Band] = (1 until self.GetRasterCount()).map(self.GetRasterBand).toList
    def getProjectionRef: Option[String] = AnyRef.synchronized(Option(self.GetProjectionRef))

    def getNoDataValue: Option[Double] = {
      val arr = Array.ofDim[java.lang.Double](1)
      self.GetRasterBand(1).GetNoDataValue(arr)
      arr.headOption.flatMap(Option(_)).map(_.doubleValue())
    }

    lazy val extent: Extent = Extent(xmin, ymin, xmax, ymax)

    lazy val rasterExtent: RasterExtent = {
      if(self.GetRasterXSize * self.GetRasterYSize > Int.MaxValue)
        sys.error(s"Cannot read this raster, cols * rows exceeds maximum array index (${self.GetRasterXSize} * ${self.GetRasterYSize})")

      RasterExtent(extent, self.GetRasterXSize, self.GetRasterYSize)
    }

    lazy val gridBounds: GridBounds = GridBounds(0, 0, self.GetRasterXSize - 1, self.GetRasterYSize - 1)

    /** GetGeoTransform, OSR objects and all related to it methods are not threadsafe */
    lazy val geoTransform: Array[Double] = AnyRef.synchronized(self.GetGeoTransform)

    lazy val xmin: Double = geoTransform(0)

    lazy val ymin: Double = geoTransform(3) + geoTransform(5) * self.GetRasterYSize

    lazy val xmax: Double = geoTransform(0) +  geoTransform(1) * self.GetRasterXSize

    lazy val ymax: Double = geoTransform(3)

    lazy val cellSize: CellSize = CellSize(geoTransform(1), math.abs(geoTransform(5)))

    /** GetGeoTransform, OSR objects and all related to it methods are not threadsafe */
    def crs: Option[CRS] = AnyRef.synchronized(getProjectionRef map { new SpatialReference(_).toCRS })
  }
}
