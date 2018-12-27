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

import geotrellis.gdal.osr.OSRSpatialReference

import org.gdal.gdal._
import org.gdal.ogr.{Feature, Geometry}

import java.nio.ByteBuffer

import scala.collection.JavaConverters._

/** Also keeps references to all base datasets to prevent the wrong GC ordering */
case class GDALDataset(underlying: Dataset) extends GDALMajorObject {
  private var selfRef: GDALDataset = this

  private var parentReferences: Array[String] = Array()

  def getParentReferences: Array[String] = parentReferences

  def setParentReferences(refs: Array[String]): GDALDataset = AnyRef.synchronized {
    parentReferences = refs
    this
  }

  private var childReference: String = ""

  def getChildReference: String = AnyRef.synchronized { childReference }

  def deleteChildReference: Unit = AnyRef.synchronized { childReference = "" }

  def setChildReference(ref: String): GDALDataset = AnyRef.synchronized {
    childReference = ref
    this
  }

  private var selfReference: String = ""

  def getSelfReference: String = AnyRef.synchronized { selfReference }

  def setSelfReference(ref: String): GDALDataset = AnyRef.synchronized {
    selfReference = ref
    this
  }

  def buildOverviews(overviewlist: Array[Int], callback: ProgressCallback): Int = {
    underlying.BuildOverviews(null, overviewlist, callback)
  }

  def buildOverviews(overviewlist: Array[Int]): Int = {
    underlying.BuildOverviews(overviewlist)
  }

  def getGCPs: Vector[GCP] = {
    val gcps = new java.util.Vector[GCP]()
    underlying.GetGCPs(gcps)
    gcps.asScala.toVector
  }

  def getGeoTransform: Array[Double] = {
    underlying.GetGeoTransform()
  }

  def getLayer(index: Int): GDALLayer = {
    GDALLayer(underlying.GetLayer(index))
  }

  def getLayer(layerName: String): GDALLayer = {
    GDALLayer(underlying.GetLayer(layerName))
  }

  def getRasterXSize: Int = {
    underlying.getRasterXSize
  }

  def getRasterYSize: Int = {
    underlying.getRasterYSize
  }

  def getRasterCount: Int = {
    underlying.getRasterCount
  }

  def getDriver: GDALDriver = {
    GDALDriver(underlying.GetDriver)
  }

  def getRasterBand(nBand: Int): GDALBand = {
    GDALBand(underlying.GetRasterBand(nBand))
  }

  def getRasterBands(seq: Seq[Int] = 1 to getRasterCount): List[GDALBand] =
    seq.map(getRasterBand).toList

  /** https://lists.osgeo.org/pipermail/gdal-dev/2007-November/014795.html */
  def getProjection: Option[String] = AnyRef.synchronized {
    Option(underlying.GetProjection).filter(_.nonEmpty)
  }

  /** https://lists.osgeo.org/pipermail/gdal-dev/2007-November/014795.html */
  def getProjectionRef: Option[String] = AnyRef.synchronized {
    Option(underlying.GetProjectionRef).filter(_.nonEmpty)
  }

  /** https://lists.osgeo.org/pipermail/gdal-dev/2007-November/014795.html */
  def setProjection(prj: String): Int = AnyRef.synchronized {
    underlying.SetProjection(prj)
  }

  def setGeoTransform(argin: Array[Double]): Int = {
    underlying.SetGeoTransform(argin)
  }

  def buildOverviews(resampling: String, overviewlist: Array[Int], callback: ProgressCallback): Int = {
    underlying.BuildOverviews(resampling, overviewlist, callback)
  }

  def buildOverviews(resampling: String, overviewlist: Array[Int]): Int = {
    underlying.BuildOverviews(resampling, overviewlist)
  }

  def getGCPCount: Int = {
    underlying.GetGCPCount
  }

  /** https://lists.osgeo.org/pipermail/gdal-dev/2007-November/014795.html */
  def getGCPProjection: String = AnyRef.synchronized {
    underlying.GetGCPProjection
  }

  def setGCPs(nGCPs: Array[GCP], pszGCPProjection: String): Int = {
    underlying.SetGCPs(nGCPs, pszGCPProjection)
  }

  def flushCache(): Unit = {
    underlying.FlushCache()
  }

  def addBand(datatype: Int, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.AddBand(datatype, vector)
  }

  def addBand(datatype: Int): Int = {
    underlying.AddBand(datatype)
  }

  def addBand: Int = {
    underlying.AddBand
  }

  def createMaskBand(nFlags: Int): Int = {
    underlying.CreateMaskBand(nFlags)
  }

  def getFileList: Vector[String] = {
    val list = underlying.GetFileList()
    if (list == null) Vector()
    else list.asScala.toVector.map(_.asInstanceOf[String])
  }

  def adviseRead(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: SWIGTYPE_p_int, buf_ysize: SWIGTYPE_p_int, buf_type: SWIGTYPE_p_int, band_list: Array[Int], options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.AdviseRead(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, band_list, vector)
  }

  def adviseRead(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: SWIGTYPE_p_int, buf_ysize: SWIGTYPE_p_int, buf_type: SWIGTYPE_p_int, band_list: Array[Int]): Int = {
    underlying.AdviseRead(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, band_list)
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

  def createLayer(name: String, srs: OSRSpatialReference, geom_type: Int, options: Vector[_]): GDALLayer = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    GDALLayer(underlying.CreateLayer(name, srs.underlying, geom_type, vector))
  }

  def createLayer(name: String, srs: OSRSpatialReference, geom_type: Int): GDALLayer = {
    GDALLayer(underlying.CreateLayer(name, srs.underlying, geom_type))
  }

  def createLayer(name: String, srs: OSRSpatialReference): GDALLayer = {
    GDALLayer(underlying.CreateLayer(name, srs.underlying))
  }

  def createLayer(name: String): GDALLayer = {
    GDALLayer(underlying.CreateLayer(name))
  }

  def copyLayer(src_layer: GDALLayer, new_name: String, options: Vector[_]): GDALLayer = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    GDALLayer(underlying.CopyLayer(src_layer.underlying, new_name, vector))
  }

  def copyLayer(src_layer: GDALLayer, new_name: String): GDALLayer = {
    GDALLayer(underlying.CopyLayer(src_layer.underlying, new_name))
  }

  def deleteLayer(index: Int): Int = {
    underlying.DeleteLayer(index)
  }

  def getLayerCount: Int = {
    underlying.GetLayerCount
  }

  def getLayerByIndex(index: Int): GDALLayer = {
    GDALLayer(underlying.GetLayerByIndex(index))
  }

  def getLayerByName(layer_name: String): GDALLayer = {
    GDALLayer(underlying.GetLayerByName(layer_name))
  }

  def resetReading(): Unit = {
    underlying.ResetReading
  }

  def getNextFeature: Feature = {
    underlying.GetNextFeature
  }

  def testCapability(cap: String): Boolean = {
    underlying.TestCapability(cap)
  }

  def executeSQL(statement: String, spatialFilter: Geometry, dialect: String): GDALLayer = {
    GDALLayer(underlying.ExecuteSQL(statement, spatialFilter, dialect))
  }

  def executeSQL(statement: String, spatialFilter: Geometry): GDALLayer = {
    GDALLayer(underlying.ExecuteSQL(statement, spatialFilter))
  }

  def executeSQL(statement: String): GDALLayer = {
    GDALLayer(underlying.ExecuteSQL(statement))
  }

  def releaseResultSet(layer: GDALLayer): Unit = {
    underlying.ReleaseResultSet(layer.underlying)
  }

  def getStyleTable: GDALStyleTable = {
    GDALStyleTable(underlying.GetStyleTable)
  }

  def setStyleTable(table: GDALStyleTable): Unit = {
    underlying.SetStyleTable(table.underlying)
  }

  def startTransaction(force: Int): Int = {
    underlying.StartTransaction(force)
  }

  def startTransaction: Int = {
    underlying.StartTransaction
  }

  def commitTransaction: Int = {
    underlying.CommitTransaction
  }

  def rollbackTransaction: Int = {
    underlying.RollbackTransaction
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, band_list, nPixelSpace, nLineSpace)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, band_list, nPixelSpace)
  }

  def readRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, band_list: Array[Int]): Int = {
    underlying.ReadRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, band_list)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Byte], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Byte], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Byte], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Byte], band_list: Array[Int]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Short], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Short], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Short], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Short], band_list: Array[Int]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Int], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Int], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Int], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Int], band_list: Array[Int]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Float], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Float], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Float], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Float], band_list: Array[Int]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Double], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Double], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace, nLineSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Double], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list, nPixelSpace)
  }

  def readRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayOut: Array[Double], band_list: Array[Int]): Int = {
    underlying.ReadRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayOut, band_list)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, band_list, nPixelSpace, nLineSpace)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, band_list, nPixelSpace)
  }

  def writeRaster_Direct(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, nioBuffer: ByteBuffer, band_list: Array[Int]): Int = {
    underlying.WriteRaster_Direct(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, nioBuffer, band_list)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Byte], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Byte], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Byte], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Byte], band_list: Array[Int]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Short], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Short], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Short], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Short], band_list: Array[Int]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Int], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Int], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Int], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Int], band_list: Array[Int]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Float], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Float], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Float], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Float], band_list: Array[Int]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Double], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int, nBandSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace, nBandSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Double], band_list: Array[Int], nPixelSpace: Int, nLineSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace, nLineSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Double], band_list: Array[Int], nPixelSpace: Int): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list, nPixelSpace)
  }

  def writeRaster(xoff: Int, yoff: Int, xsize: Int, ysize: Int, buf_xsize: Int, buf_ysize: Int, buf_type: Int, regularArrayIn: Array[Double], band_list: Array[Int]): Int = {
    underlying.WriteRaster(xoff, yoff, xsize, ysize, buf_xsize, buf_ysize, buf_type, regularArrayIn, band_list)
  }

  def delete: Unit = {
    if (underlying != null) underlying.delete
  }

  // TODO: review this approach
  // it looks like it never gets into the children nonEmpty clause
  override def finalize(): Unit = {
    // println(s"underlying != null: ${underlying != null}")
    if (underlying != null) {
      if(getChildReference.isEmpty) {
        underlying.delete
        super.finalize()
      } else {
        // it never goes there for some reason ._.
        selfRef = this
      }
    }
  }
}
