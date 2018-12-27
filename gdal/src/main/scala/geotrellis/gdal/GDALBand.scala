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

package geotrellis.gdal

import org.gdal.gdal._

import scala.collection.JavaConverters._
import java.nio.ByteBuffer

case class GDALBand(underlying: Band) extends GDALMajorObject {
  def getXSize: Int = {
    underlying.GetXSize
  }

  def getYSize: Int = {
    underlying.getYSize
  }

  def getRasterDataType: Int = {
    underlying.GetRasterDataType
  }

  def getBlockXSize: Int = {
    underlying.GetBlockXSize
  }

  def getBlockYSize: Int = {
    underlying.GetBlockYSize
  }

  def checksum: Int = {
    underlying.Checksum
  }

  def getStatistics(approx_ok: Boolean, force: Boolean, min: Array[Double], max: Array[Double], mean: Array[Double], stddev: Array[Double]): Int = {
    underlying.GetStatistics(approx_ok, force, min, max, mean, stddev)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, nioBuffer: ByteBuffer): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, nioBuffer)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, nioBuffer: ByteBuffer): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, nioBuffer)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int): ByteBuffer = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int): ByteBuffer = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_type)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int): ByteBuffer = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Byte]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Byte]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Short]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Short]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Int]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Int]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Float]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Float]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Double]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Double]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, array)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, nioBuffer: ByteBuffer): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, nioBuffer)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, nioBuffer: ByteBuffer): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, nioBuffer)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, nioBuffer: ByteBuffer): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, nioBuffer)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Byte]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Byte]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Short]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Short]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Int]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Int]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Float]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Float]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_type: Int, array: Array[Double]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_type, array)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, array: Array[Double]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, array)
  }

  def getDataType: Int = {
    underlying.getDataType
  }

  def getGDALDataType: GDALDataType = {
    underlying.getDataType
  }

  def getDataset: GDALDataset = {
    GDALDataset(underlying.GetDataset)
  }

  def getBand: Int = {
    underlying.GetBand
  }

  def getBlockSize(pnBlockXSize: Array[Int], pnBlockYSize: Array[Int]): Unit = {
    underlying.GetBlockSize(pnBlockXSize, pnBlockYSize)
  }

  def getColorInterpretation: Int = {
    underlying.GetColorInterpretation
  }

  def getColorInterpretationName: String = {
    gdal.GetColorInterpretationName(getColorInterpretation)
  }

  def getRasterColorInterpretation: Int = {
    underlying.GetRasterColorInterpretation
  }

  def setColorInterpretation(value: Int): Int = {
    underlying.SetColorInterpretation(value)
  }

  def setRasterColorInterpretation(value: Int): Int = {
    underlying.SetRasterColorInterpretation(value)
  }

  def getNoDataValue: Option[Double] = {
    val arr = Array.ofDim[java.lang.Double](1)
    underlying.GetNoDataValue(arr)
    arr.headOption.flatMap(Option(_)).map(_.doubleValue())
  }

  def setNoDataValue(d: Double): Int = {
    underlying.SetNoDataValue(d)
  }

  def deleteNoDataValue: Int = {
    underlying.DeleteNoDataValue
  }

  def getUnitType: String = {
    underlying.GetUnitType
  }

  def setUnitType(value: String): Int = {
    underlying.SetUnitType(value)
  }

  def getRasterCategoryNames: Vector[String] = {
    underlying.GetRasterCategoryNames().asScala.toVector.map(_.asInstanceOf[String])
  }

  def setRasterCategoryNames(names: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(names.asJavaCollection)
    underlying.SetRasterCategoryNames(vector)
  }

  def getMinimum: Option[Double] = {
    val arr = Array.ofDim[java.lang.Double](1)
    underlying.GetMinimum(arr)
    arr.headOption.flatMap(Option(_)).map(_.doubleValue())
  }

  def getMaximum: Option[Double] = {
    val arr = Array.ofDim[java.lang.Double](1)
    underlying.GetMaximum(arr)
    arr.headOption.flatMap(Option(_)).map(_.doubleValue())
  }

  def getOffset: Option[Double] = {
    val arr = Array.ofDim[java.lang.Double](1)
    underlying.GetOffset(arr)
    arr.headOption.flatMap(Option(_)).map(_.doubleValue())
  }

  def getScale: Option[Double] = {
    val arr = Array.ofDim[java.lang.Double](1)
    underlying.GetScale(arr)
    arr.headOption.flatMap(Option(_)).map(_.doubleValue())
  }

  def setOffset(value: Double): Int = {
    underlying.SetOffset(value)
  }

  def setScale(value: Double): Int = {
    underlying.SetScale(value)
  }

  def getStatistics(approx_ok: Int, force: Int, min: Array[Double], max: Array[Double], mean: Array[Double], stddev: Array[Double]): Int = {
    underlying.GetStatistics(approx_ok, force, min, max, mean, stddev)
  }

  def computeStatistics(approx_ok: Boolean, min: Array[Double], max: Array[Double], mean: Array[Double], stddev: Array[Double], callback: ProgressCallback): Int = {
    underlying.ComputeStatistics(approx_ok, min, max, mean, stddev, callback)
  }

  def computeStatistics(approx_ok: Boolean, min: Array[Double], max: Array[Double], mean: Array[Double], stddev: Array[Double]): Int = {
    underlying.ComputeStatistics(approx_ok, min, max, mean, stddev)
  }

  def computeStatistics(approx_ok: Boolean, min: Array[Double], max: Array[Double], mean: Array[Double]): Int = {
    underlying.ComputeStatistics(approx_ok, min, max, mean)
  }

  def computeStatistics(approx_ok: Boolean, min: Array[Double], max: Array[Double]): Int = {
    underlying.ComputeStatistics(approx_ok, min, max)
  }

  def computeStatistics(approx_ok: Boolean, min: Array[Double]): Int = {
    underlying.ComputeStatistics(approx_ok, min)
  }

  def computeStatistics(approx_ok: Boolean): Int = {
    underlying.ComputeStatistics(approx_ok)
  }

  def setStatistics(min: Double, max: Double, mean: Double, stddev: Double): Int = {
    underlying.SetStatistics(min, max, mean, stddev)
  }

  def getOverviewCount: Int = {
    underlying.GetOverviewCount
  }

  def getOverview(i: Int): GDALBand = {
    GDALBand(underlying.GetOverview(i))
  }

  def checksum(xoff: Int, yoff: Int, xsize: Int, ysize: Int): Int = {
    underlying.Checksum(xoff, yoff, xsize, ysize)
  }

  def computeRasterMinMax(approx_ok: Int): Option[(Double, Double)] = {
    val arr = Array.ofDim[Double](2)
    underlying.ComputeRasterMinMax(arr, approx_ok)
    if (arr.length == 2) Some(arr(0) -> arr(1))
    else None
  }

  def computeRasterMinMax: Option[(Double, Double)] = {
    val arr = Array.ofDim[Double](2)
    underlying.ComputeRasterMinMax(arr)
    if (arr.length == 2) Some(arr(0) -> arr(1))
    else None
  }

  def computeBandStats(samplestep: Int): Option[(Double, Double)] = {
    val arr = Array.ofDim[Double](2)
    underlying.ComputeBandStats(arr, samplestep)
    if (arr.length == 2) Some(arr(0) -> arr(1))
    else None
  }

  def computeBandStats: Option[(Double, Double)] = {
    val arr = Array.ofDim[Double](2)
    underlying.ComputeBandStats(arr)
    if (arr.length == 2) Some(arr(0) -> arr(1))
    else None
  }

  def fill(real_fill: Double, imag_fill: Double): Int = {
    underlying.Fill(real_fill, imag_fill)
  }

  def fill(real_fill: Double): Int = {
    underlying.Fill(real_fill)
  }

  def flushCache: Unit = {
    underlying.FlushCache
  }

  def getRasterColorTable: GDALColorTable = {
    GDALColorTable(underlying.GetRasterColorTable)
  }

  def getColorTable: GDALColorTable = {
    GDALColorTable(underlying.GetColorTable)
  }

  def setRasterColorTable(arg: GDALColorTable): Int = {
    underlying.SetRasterColorTable(arg.underlying)
  }

  def setColorTable(arg: GDALColorTable): Int = {
    underlying.SetColorTable(arg.underlying)
  }

  def GetDefaultRAT: GDALRasterAttributeTable = {
    GDALRasterAttributeTable(underlying.GetDefaultRAT)
  }

  def setDefaultRAT(table: GDALRasterAttributeTable): Int = {
    underlying.SetDefaultRAT(table.underlying)
  }

  def getMaskBand: GDALBand = {
    GDALBand(underlying.GetMaskBand)
  }

  def getMaskFlags: Int = {
    underlying.GetMaskFlags
  }

  def createMaskBand(nFlags: Int): Int = {
    underlying.CreateMaskBand(nFlags)
  }

  def setDefaultHistogram(min: Double, max: Double, buckets_in: Array[Int]): Int = {
    underlying.SetDefaultHistogram(min, max, buckets_in)
  }

  def hasArbitraryOverviews: Boolean = {
    underlying.HasArbitraryOverviews
  }

  def getCategoryNames: Vector[_] = {
    underlying.GetCategoryNames.asScala.toVector
  }

  def setCategoryNames(papszCategoryNames: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(papszCategoryNames.asJavaCollection)
    underlying.SetCategoryNames(vector)
  }

  def adviseRead(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: SWIGTYPE_p_int, buf_ysize: SWIGTYPE_p_int, buf_type: SWIGTYPE_p_int, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.AdviseRead(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, vector)
  }

  def adviseRead(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: SWIGTYPE_p_int, buf_ysize: SWIGTYPE_p_int, buf_type: SWIGTYPE_p_int): Int = {
    underlying.AdviseRead(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type)
  }

  def adviseRead(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: SWIGTYPE_p_int, buf_ysize: SWIGTYPE_p_int): Int = {
    underlying.AdviseRead(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize)
  }

  def adviseRead(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: SWIGTYPE_p_int): Int = {
    underlying.AdviseRead(xoff, yoff, xsize, ysize, buf_xsize)
  }

  def adviseRead(xoff: Int, yoff: Int, xsize: Int, ysize: Int): Int = {
    underlying.AdviseRead(xoff, yoff, xsize, ysize)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, nPixelSpace, nLineSpace)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, nPixelSpace: Int): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, nPixelSpace)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Byte], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Byte], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Byte]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Short], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Short], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Short]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Int], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Int]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Float], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Float], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Float]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Double], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Double], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Double]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, nPixelSpace, nLineSpace)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, nPixelSpace: Int): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, nPixelSpace)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Byte], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Byte], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Byte]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Short], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Short], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Short]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Int], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Int]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Float], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Float], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Float]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Double], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Double], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Double]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn)
  }

  def readBlock_Direct(nXBlockOff: Int, nYBlockOff: Int, nioBuffer: ByteBuffer): Int = {
    underlying.ReadBlock_Direct(nXBlockOff, nYBlockOff, nioBuffer)
  }

  def writeBlock_Direct(nXBlockOff: Int, nYBlockOff: Int, nioBuffer: ByteBuffer): Int = {
    underlying.WriteBlock_Direct(nXBlockOff, nYBlockOff, nioBuffer)
  }

  def getHistogram(min: Double, max: Double, buckets: Array[Int], include_out_of_range: Boolean, approx_ok: Boolean, callback: ProgressCallback): Int = {
    underlying.GetHistogram(min, max, buckets, include_out_of_range, approx_ok, callback)
  }

  def getHistogram(min: Double, max: Double, buckets: Array[Int], include_out_of_range: Boolean, approx_ok: Boolean): Int = {
    underlying.GetHistogram(min, max, buckets, include_out_of_range, approx_ok)
  }

  def getHistogram(min: Double, max: Double, buckets: Array[Int]): Int = {
    underlying.GetHistogram(min, max, buckets)
  }

  def getHistogram(buckets: Array[Int]): Int = {
    underlying.GetHistogram(buckets)
  }

  def getDefaultHistogram(min_ret: Array[Double], max_ret: Array[Double], buckets_ret: Array[Array[Int]], force: Boolean, callback: ProgressCallback): Int = {
    underlying.GetDefaultHistogram(min_ret, max_ret, buckets_ret, force, callback)
  }

  def getDefaultHistogram(min_ret: Array[Double], max_ret: Array[Double], buckets_ret: Array[Array[Int]], force: Boolean): Int = {
    underlying.GetDefaultHistogram(min_ret, max_ret, buckets_ret, force)
  }

  def getDefaultHistogram(min_ret: Array[Double], max_ret: Array[Double], buckets_ret: Array[Array[Int]]): Int = {
    underlying.GetDefaultHistogram(min_ret, max_ret, buckets_ret)
  }

  def delete: Unit = {
    if (underlying != null) underlying.delete
  }
}
