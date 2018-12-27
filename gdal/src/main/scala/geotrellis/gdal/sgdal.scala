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
import java.nio.ByteBuffer
import scala.collection.JavaConverters._

object sgdal {
  def useExceptions: Unit = {
    gdal.UseExceptions
  }

  def dontUseExceptions: Unit = {
    gdal.DontUseExceptions
  }

  def generalCmdLineProcessor(args: Array[String], nOptions: Int): Array[String] = {
    gdal.GeneralCmdLineProcessor(args, nOptions)
  }

  def generalCmdLineProcessor(args: Array[String]): Array[String] = {
    gdal.GeneralCmdLineProcessor(args)
  }

  def invGeoTransform(gt_in: Array[Double]): Array[Double] = {
    gdal.InvGeoTransform(gt_in)
  }

  def debug(msg_class: String, message: String): Unit = {
    gdal.Debug(msg_class, message)
  }

  def setErrorHandler(pszCallbackName: String): Int = {
    gdal.SetErrorHandler(pszCallbackName)
  }

  def setErrorHandler: Int = {
    gdal.SetErrorHandler
  }

  def pushErrorHandler(pszCallbackName: String): Int = {
    gdal.PushErrorHandler(pszCallbackName)
  }

  def pushErrorHandler: Int = {
    gdal.PushErrorHandler
  }

  def error(msg_class: Int, err_code: Int, msg: String): Unit = {
    gdal.Error(msg_class, err_code, msg)
  }

  def GOA2GetAuthorizationURL(pszScope: String): String = {
    gdal.GOA2GetAuthorizationURL(pszScope)
  }

  def GOA2GetRefreshToken(pszAuthToken: String, pszScope: String): String = {
    gdal.GOA2GetRefreshToken(pszAuthToken, pszScope)
  }

  def GOA2GetAccessToken(pszRefreshToken: String, pszScope: String): String = {
    gdal.GOA2GetAccessToken(pszRefreshToken, pszScope)
  }

  def popErrorHandler: Unit = {
    gdal.PopErrorHandler
  }

  def errorReset: Unit = {
    gdal.ErrorReset
  }

  def escapeString(len: Array[Byte], scheme: Int): String = {
    gdal.EscapeString(len, scheme)
  }

  def escapeString(str: String, scheme: Int): String = {
    gdal.EscapeString(str, scheme)
  }

  def getLastErrorNo: Int = {
    gdal.GetLastErrorNo
  }

  def getLastErrorType: Int = {
    gdal.GetLastErrorType
  }

  def getLastErrorMsg: String = {
    gdal.GetLastErrorMsg
  }

  def getErrorCounter: Long = {
    gdal.GetErrorCounter
  }

  def VSIGetLastErrorNo: Int = {
    gdal.VSIGetLastErrorNo
  }

  def VSIGetLastErrorMsg: String = {
    gdal.VSIGetLastErrorMsg
  }

  def pushFinderLocation(utf8_path: String): Unit = {
    gdal.PushFinderLocation(utf8_path)
  }

  def popFinderLocation: Unit = {
    gdal.PopFinderLocation
  }

  def finderClean(): Unit = {
    gdal.FinderClean
  }

  def findFile(pszClass: String, utf8_path: String): String = {
    gdal.FindFile(pszClass, utf8_path)
  }

  def readDir(utf8_path: String, nMaxFiles: Int): Vector[String] = {
    gdal.ReadDir(utf8_path, nMaxFiles).asScala.toVector.map(_.asInstanceOf[String])
  }

  def readDir(utf8_path: String): Vector[String] = {
    gdal.ReadDir(utf8_path).asScala.toVector.map(_.asInstanceOf[String])
  }

  def readDirRecursive(utf8_path: String): Vector[String] = {
    gdal.ReadDirRecursive(utf8_path).asScala.toVector.map(_.asInstanceOf[String])
  }

  def setConfigOption(pszKey: String, pszValue: String): Unit = {
    gdal.SetConfigOption(pszKey, pszValue)
  }

  def getConfigOption(pszKey: String, pszDefault: String): String = {
    gdal.GetConfigOption(pszKey, pszDefault)
  }

  def getConfigOption(pszKey: String): String = {
    gdal.GetConfigOption(pszKey)
  }

  def CPLBinaryToHex(nBytes: Array[Byte]): String = {
    gdal.CPLBinaryToHex(nBytes)
  }

  def CPLHexToBinary(pszHex: String): Array[Byte] = {
    gdal.CPLHexToBinary(pszHex)
  }

  def fileFromMemBuffer(utf8_path: String, nBytes: Array[Byte]): Unit = {
    gdal.FileFromMemBuffer(utf8_path, nBytes)
  }

  def unlink(utf8_path: String): Int = {
    gdal.Unlink(utf8_path)
  }

  def hasThreadSupport: Int = {
    gdal.HasThreadSupport
  }

  def mkdir(utf8_path: String, mode: Int): Int = {
    gdal.Mkdir(utf8_path, mode)
  }

  def rmdir(utf8_path: String): Int = {
    gdal.Rmdir(utf8_path)
  }

  def mkdirRecursive(utf8_path: String, mode: Int): Int = {
    gdal.MkdirRecursive(utf8_path, mode)
  }

  def rmdirRecursive(utf8_path: String): Int = {
    gdal.RmdirRecursive(utf8_path)
  }

  def rename(pszOld: String, pszNew: String): Int = {
    gdal.Rename(pszOld, pszNew)
  }

  def getActualURL(utf8_path: String): String = {
    gdal.GetActualURL(utf8_path)
  }

  def getSignedURL(utf8_path: String, options: Vector[_]): String = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.GetSignedURL(utf8_path, vector)
  }

  def getSignedURL(utf8_path: String): String = {
    gdal.GetSignedURL(utf8_path)
  }

  def getFileSystemsPrefixes: Vector[String] = {
    gdal.GetFileSystemsPrefixes.asScala.toVector.map(_.asInstanceOf[String])
  }

  def getFileSystemOptions(utf8_path: String): String = {
    gdal.GetFileSystemOptions(utf8_path)
  }

  def parseCommandLine(utf8_path: String): Vector[String] = {
    gdal.ParseCommandLine(utf8_path).asScala.toVector.map(_.asInstanceOf[String])
  }

  def GDAL_GCP_GCPX_get(gcp: GCP): Double = {
    gdal.GDAL_GCP_GCPX_get(gcp)
  }

  def GDAL_GCP_GCPX_set(gcp: GCP, dfGCPX: Double): Unit = {
    gdal.GDAL_GCP_GCPX_set(gcp, dfGCPX)
  }

  def GDAL_GCP_GCPY_get(gcp: GCP): Double = {
    gdal.GDAL_GCP_GCPY_get(gcp)
  }

  def GDAL_GCP_GCPY_set(gcp: GCP, dfGCPY: Double): Unit = {
    gdal.GDAL_GCP_GCPY_set(gcp, dfGCPY)
  }

  def GDAL_GCP_GCPZ_get(gcp: GCP): Double = {
    gdal.GDAL_GCP_GCPZ_get(gcp)
  }

  def GDAL_GCP_GCPZ_set(gcp: GCP, dfGCPZ: Double): Unit = {
    gdal.GDAL_GCP_GCPZ_set(gcp, dfGCPZ)
  }

  def GDAL_GCP_GCPPixel_get(gcp: GCP): Double = {
    gdal.GDAL_GCP_GCPPixel_get(gcp)
  }

  def GDAL_GCP_GCPPixel_set(gcp: GCP, dfGCPPixel: Double): Unit = {
    gdal.GDAL_GCP_GCPPixel_set(gcp, dfGCPPixel)
  }

  def GDAL_GCP_GCPLine_get(gcp: GCP): Double = {
    gdal.GDAL_GCP_GCPLine_get(gcp)
  }

  def GDAL_GCP_GCPLine_set(gcp: GCP, dfGCPLine: Double): Unit = {
    gdal.GDAL_GCP_GCPLine_set(gcp, dfGCPLine)
  }

  def GDAL_GCP_Info_get(gcp: GCP): String = {
    gdal.GDAL_GCP_Info_get(gcp)
  }

  def GDAL_GCP_Info_set(gcp: GCP, pszInfo: String): Unit = {
    gdal.GDAL_GCP_Info_set(gcp, pszInfo)
  }

  def GDAL_GCP_Id_get(gcp: GCP): String = {
    gdal.GDAL_GCP_Id_get(gcp)
  }

  def GDAL_GCP_Id_set(gcp: GCP, pszId: String): Unit = {
    gdal.GDAL_GCP_Id_set(gcp, pszId)
  }

  def GCPsToGeoTransform(nGCPs: Array[GCP], argout: Array[Double], bApproxOK: Int): Int = {
    gdal.GCPsToGeoTransform(nGCPs, argout, bApproxOK)
  }

  def GCPsToGeoTransform(nGCPs: Array[GCP], argout: Array[Double]): Int = {
    gdal.GCPsToGeoTransform(nGCPs, argout)
  }

  def computeMedianCutPCT(red: GDALBand, green: GDALBand, blue: GDALBand, num_colors: Int, colors: GDALColorTable, callback: ProgressCallback): Int = {
    gdal.ComputeMedianCutPCT(red.underlying, green.underlying, blue.underlying, num_colors, colors.underlying, callback)
  }

  def computeMedianCutPCT(red: GDALBand, green: GDALBand, blue: GDALBand, num_colors: Int, colors: GDALColorTable): Int = {
    gdal.ComputeMedianCutPCT(red.underlying, green.underlying, blue.underlying, num_colors, colors.underlying)
  }

  def ditherRGB2PCT(red: GDALBand, green: GDALBand, blue: GDALBand, target: GDALBand, colors: GDALColorTable, callback: ProgressCallback): Int = {
    gdal.DitherRGB2PCT(red.underlying, green.underlying, blue.underlying, target.underlying, colors.underlying, callback)
  }

  def ditherRGB2PCT(red: GDALBand, green: GDALBand, blue: GDALBand, target: GDALBand, colors: GDALColorTable): Int = {
    gdal.DitherRGB2PCT(red.underlying, green.underlying, blue.underlying, target.underlying, colors.underlying)
  }

  def reprojectImage(src_ds: GDALDataset, dst_ds: GDALDataset, src_wkt: String, dst_wkt: String, eResampleAlg: Int, WarpMemoryLimit: Double, maxerror: Double, callback: ProgressCallback, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.ReprojectImage(src_ds.underlying, dst_ds.underlying, src_wkt, dst_wkt, eResampleAlg, WarpMemoryLimit, maxerror, callback, vector)
  }

  def reprojectImage(src_ds: GDALDataset, dst_ds: GDALDataset, src_wkt: String, dst_wkt: String, eResampleAlg: Int, WarpMemoryLimit: Double, maxerror: Double, callback: ProgressCallback): Int = {
    gdal.ReprojectImage(src_ds.underlying, dst_ds.underlying, src_wkt, dst_wkt, eResampleAlg, WarpMemoryLimit, maxerror, callback)
  }

  def reprojectImage(src_ds: GDALDataset, dst_ds: GDALDataset, src_wkt: String, dst_wkt: String, eResampleAlg: Int, WarpMemoryLimit: Double, maxerror: Double): Int = {
    gdal.ReprojectImage(src_ds.underlying, dst_ds.underlying, src_wkt, dst_wkt, eResampleAlg, WarpMemoryLimit, maxerror)
  }

  def reprojectImage(src_ds: GDALDataset, dst_ds: GDALDataset, src_wkt: String, dst_wkt: String, eResampleAlg: Int, WarpMemoryLimit: Double): Int = {
    gdal.ReprojectImage(src_ds.underlying, dst_ds.underlying, src_wkt, dst_wkt, eResampleAlg, WarpMemoryLimit)
  }

  def reprojectImage(src_ds: GDALDataset, dst_ds: GDALDataset, src_wkt: String, dst_wkt: String, eResampleAlg: Int): Int = {
    gdal.ReprojectImage(src_ds.underlying, dst_ds.underlying, src_wkt, dst_wkt, eResampleAlg)
  }

  def reprojectImage(src_ds: GDALDataset, dst_ds: GDALDataset, src_wkt: String, dst_wkt: String): Int = {
    gdal.ReprojectImage(src_ds.underlying, dst_ds.underlying, src_wkt, dst_wkt)
  }

  def reprojectImage(src_ds: GDALDataset, dst_ds: GDALDataset, src_wkt: String): Int = {
    gdal.ReprojectImage(src_ds.underlying, dst_ds.underlying, src_wkt)
  }

  def reprojectImage(src_ds: GDALDataset, dst_ds: GDALDataset): Int = {
    gdal.ReprojectImage(src_ds.underlying, dst_ds.underlying)
  }

  def computeProximity(srcBand: GDALBand, proximityBand: GDALBand, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.ComputeProximity(srcBand.underlying, proximityBand.underlying, vector, callback)
  }

  def computeProximity(srcBand: GDALBand, proximityBand: GDALBand, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.ComputeProximity(srcBand.underlying, proximityBand.underlying, vector)
  }

  def computeProximity(srcBand: GDALBand, proximityBand: GDALBand): Int = {
    gdal.ComputeProximity(srcBand.underlying, proximityBand.underlying)
  }

  def rasterizeLayer(dataset: GDALDataset, bands: Array[Int], layer: GDALLayer, burn_values: Array[Double], options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.RasterizeLayer(dataset.underlying, bands, layer.underlying, burn_values, vector, callback)
  }

  def rasterizeLayer(dataset: GDALDataset, bands: Array[Int], layer: GDALLayer, burn_values: Array[Double], options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.RasterizeLayer(dataset.underlying, bands, layer.underlying, burn_values, vector)
  }

  def rasterizeLayer(dataset: GDALDataset, bands: Array[Int], layer: GDALLayer, burn_values: Array[Double]): Int = {
    gdal.RasterizeLayer(dataset.underlying, bands, layer.underlying, burn_values)
  }

  def rasterizeLayer(dataset: GDALDataset, bands: Array[Int], layer: GDALLayer): Int = {
    gdal.RasterizeLayer(dataset.underlying, bands, layer.underlying)
  }

  def polygonize(srcBand: GDALBand, maskBand: GDALBand, outLayer: GDALLayer, iPixValField: Int, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.Polygonize(srcBand.underlying, maskBand.underlying, outLayer.underlying, iPixValField, vector, callback)
  }

  def polygonize(srcBand: GDALBand, maskBand: GDALBand, outLayer: GDALLayer, iPixValField: Int, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.Polygonize(srcBand.underlying, maskBand.underlying, outLayer.underlying, iPixValField, vector)
  }

  def polygonize(srcBand: GDALBand, maskBand: GDALBand, outLayer: GDALLayer, iPixValField: Int): Int = {
    gdal.Polygonize(srcBand.underlying, maskBand.underlying, outLayer.underlying, iPixValField)
  }

  def fPolygonize(srcBand: GDALBand, maskBand: GDALBand, outLayer: GDALLayer, iPixValField: Int, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.FPolygonize(srcBand.underlying, maskBand.underlying, outLayer.underlying, iPixValField, vector, callback)
  }

  def fPolygonize(srcBand: GDALBand, maskBand: GDALBand, outLayer: GDALLayer, iPixValField: Int, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.FPolygonize(srcBand.underlying, maskBand.underlying, outLayer.underlying, iPixValField, vector)
  }

  def fPolygonize(srcBand: GDALBand, maskBand: GDALBand, outLayer: GDALLayer, iPixValField: Int): Int = {
    gdal.FPolygonize(srcBand.underlying, maskBand.underlying, outLayer.underlying, iPixValField)
  }

  def fillNodata(targetBand: GDALBand, maskBand: GDALBand, maxSearchDist: Double, smoothingIterations: Int, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.FillNodata(targetBand.underlying, maskBand.underlying, maxSearchDist, smoothingIterations, vector, callback)
  }

  def fillNodata(targetBand: GDALBand, maskBand: GDALBand, maxSearchDist: Double, smoothingIterations: Int, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.FillNodata(targetBand.underlying, maskBand.underlying, maxSearchDist, smoothingIterations, vector)
  }

  def fillNodata(targetBand: GDALBand, maskBand: GDALBand, maxSearchDist: Double, smoothingIterations: Int): Int = {
    gdal.FillNodata(targetBand.underlying, maskBand.underlying, maxSearchDist, smoothingIterations)
  }

  def sieveFilter(srcBand: GDALBand, maskBand: GDALBand, dstBand: GDALBand, threshold: Int, connectedness: Int, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.SieveFilter(srcBand.underlying, maskBand.underlying, dstBand.underlying, threshold, connectedness, vector, callback)
  }

  def sieveFilter(srcBand: GDALBand, maskBand: GDALBand, dstBand: GDALBand, threshold: Int, connectedness: Int, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.SieveFilter(srcBand.underlying, maskBand.underlying, dstBand.underlying, threshold, connectedness, vector)
  }

  def sieveFilter(srcBand: GDALBand, maskBand: GDALBand, dstBand: GDALBand, threshold: Int, connectedness: Int): Int = {
    gdal.SieveFilter(srcBand.underlying, maskBand.underlying, dstBand.underlying, threshold, connectedness)
  }

  def sieveFilter(srcBand: GDALBand, maskBand: GDALBand, dstBand: GDALBand, threshold: Int): Int = {
    gdal.SieveFilter(srcBand.underlying, maskBand.underlying, dstBand.underlying, threshold)
  }

  def regenerateOverviews(srcBand: GDALBand, overviewBandCount: Array[GDALBand], resampling: String, callback: ProgressCallback): Int = {
    gdal.RegenerateOverviews(srcBand.underlying, overviewBandCount.map(_.underlying), resampling, callback)
  }

  def regenerateOverviews(srcBand: GDALBand, overviewBandCount: Array[GDALBand], resampling: String): Int = {
    gdal.RegenerateOverviews(srcBand.underlying, overviewBandCount.map(_.underlying), resampling)
  }

  def regenerateOverviews(srcBand: GDALBand, overviewBandCount: Array[GDALBand]): Int = {
    gdal.RegenerateOverviews(srcBand.underlying, overviewBandCount.map(_.underlying))
  }

  def regenerateOverview(srcBand: GDALBand, overviewBand: GDALBand, resampling: String, callback: ProgressCallback): Int = {
    gdal.RegenerateOverview(srcBand.underlying, overviewBand.underlying, resampling, callback)
  }

  def regenerateOverview(srcBand: GDALBand, overviewBand: GDALBand, resampling: String): Int = {
    gdal.RegenerateOverview(srcBand.underlying, overviewBand.underlying, resampling)
  }

  def regenerateOverview(srcBand: GDALBand, overviewBand: GDALBand): Int = {
    gdal.RegenerateOverview(srcBand.underlying, overviewBand.underlying)
  }

  def gridCreate(algorithmOptions: String, points: Array[Array[Double]], xMin: Double, xMax: Double, yMin: Double, yMax: Double, xSize: Int, ySize: Int, dataType: Int, nioBuffer: ByteBuffer, callback: ProgressCallback): Int = {
    gdal.GridCreate(algorithmOptions, points, xMin, xMax, yMin, yMax, xSize, ySize, dataType, nioBuffer, callback)
  }

  def gridCreate(algorithmOptions: String, points: Array[Array[Double]], xMin: Double, xMax: Double, yMin: Double, yMax: Double, xSize: Int, ySize: Int, dataType: Int, nioBuffer: ByteBuffer): Int = {
    gdal.GridCreate(algorithmOptions, points, xMin, xMax, yMin, yMax, xSize, ySize, dataType, nioBuffer)
  }

  def contourGenerate(srcBand: GDALBand, contourInterval: Double, contourBase: Double, fixedLevelCount: Array[Double], useNoData: Int, noDataValue: Double, dstLayer: GDALLayer, idField: Int, elevField: Int, callback: ProgressCallback): Int = {
    gdal.ContourGenerate(srcBand.underlying, contourInterval, contourBase, fixedLevelCount, useNoData, noDataValue, dstLayer.underlying, idField, elevField, callback)
  }

  def contourGenerate(srcBand: GDALBand, contourInterval: Double, contourBase: Double, fixedLevelCount: Array[Double], useNoData: Int, noDataValue: Double, dstLayer: GDALLayer, idField: Int, elevField: Int): Int = {
    gdal.ContourGenerate(srcBand.underlying, contourInterval, contourBase, fixedLevelCount, useNoData, noDataValue, dstLayer.underlying, idField, elevField)
  }

  def autoCreateWarpedVRT(src_ds: GDALDataset, src_wkt: String, dst_wkt: String, eResampleAlg: Int, maxerror: Double): GDALDataset = {
    GDALDataset(gdal.AutoCreateWarpedVRT(src_ds.underlying, src_wkt, dst_wkt, eResampleAlg, maxerror))
  }

  def autoCreateWarpedVRT(src_ds: GDALDataset, src_wkt: String, dst_wkt: String, eResampleAlg: Int): GDALDataset = {
    GDALDataset(gdal.AutoCreateWarpedVRT(src_ds.underlying, src_wkt, dst_wkt, eResampleAlg))
  }

  def autoCreateWarpedVRT(src_ds: GDALDataset, src_wkt: String, dst_wkt: String): GDALDataset = {
    GDALDataset(gdal.AutoCreateWarpedVRT(src_ds.underlying, src_wkt, dst_wkt))
  }

  def autoCreateWarpedVRT(src_ds: GDALDataset, src_wkt: String): GDALDataset = {
    GDALDataset(gdal.AutoCreateWarpedVRT(src_ds.underlying, src_wkt))
  }

  def autoCreateWarpedVRT(src_ds: GDALDataset): GDALDataset = {
    GDALDataset(gdal.AutoCreateWarpedVRT(src_ds.underlying))
  }

  def createPansharpenedVRT(pszXML: String, panchroBand: GDALBand, nInputSpectralBands: Array[GDALBand]): GDALDataset = {
    GDALDataset(gdal.CreatePansharpenedVRT(pszXML, panchroBand.underlying, nInputSpectralBands.map(_.underlying)))
  }

  def applyVerticalShiftGrid(src_ds: GDALDataset, grid_ds: GDALDataset, inverse: Boolean, srcUnitToMeter: Double, dstUnitToMeter: Double, options: Vector[_]): GDALDataset = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    GDALDataset(gdal.ApplyVerticalShiftGrid(src_ds.underlying, grid_ds.underlying, inverse, srcUnitToMeter, dstUnitToMeter, vector))
  }

  def applyVerticalShiftGrid(src_ds: GDALDataset, grid_ds: GDALDataset, inverse: Boolean, srcUnitToMeter: Double, dstUnitToMeter: Double): GDALDataset = {
    GDALDataset(gdal.ApplyVerticalShiftGrid(src_ds.underlying, grid_ds.underlying, inverse, srcUnitToMeter, dstUnitToMeter))
  }

  def applyVerticalShiftGrid(src_ds: GDALDataset, grid_ds: GDALDataset, inverse: Boolean, srcUnitToMeter: Double): GDALDataset = {
    GDALDataset(gdal.ApplyVerticalShiftGrid(src_ds.underlying, grid_ds.underlying, inverse, srcUnitToMeter))
  }

  def applyVerticalShiftGrid(src_ds: GDALDataset, grid_ds: GDALDataset, inverse: Boolean): GDALDataset = {
    GDALDataset(gdal.ApplyVerticalShiftGrid(src_ds.underlying, grid_ds.underlying, inverse))
  }

  def applyVerticalShiftGrid(src_ds: GDALDataset, grid_ds: GDALDataset): GDALDataset = {
    GDALDataset(gdal.ApplyVerticalShiftGrid(src_ds.underlying, grid_ds.underlying))
  }

  def applyGeoTransform(padfGeoTransform: Array[Double], dfPixel: Double, dfLine: Double, pdfGeoX: Array[Double], pdfGeoY: Array[Double]): Unit = {
    gdal.ApplyGeoTransform(padfGeoTransform, dfPixel, dfLine, pdfGeoX, pdfGeoY)
  }

  def invGeoTransform(gt_in: Array[Double], gt_out: Array[Double]): Int = {
    gdal.InvGeoTransform(gt_in, gt_out)
  }

  def versionInfo(request: String): String = {
    gdal.VersionInfo(request)
  }

  def versionInfo: String = {
    gdal.VersionInfo
  }

  def AllRegister: Unit = {
    gdal.AllRegister
  }

  def GDALDestroyDriverManager(): Unit = {
    gdal.GDALDestroyDriverManager
  }

  def getCacheMax: Int = {
    gdal.GetCacheMax
  }

  def getCacheUsed: Int = {
    gdal.GetCacheUsed
  }

  def setCacheMax(nBytes: Int): Unit = {
    gdal.SetCacheMax(nBytes)
  }

  def getDataTypeSize(eDataType: Int): Int = {
    gdal.GetDataTypeSize(eDataType)
  }

  def dataTypeIsComplex(eDataType: Int): Int = {
    gdal.DataTypeIsComplex(eDataType)
  }

  def getDataTypeName(eDataType: Int): String = {
    gdal.GetDataTypeName(eDataType)
  }

  def getDataTypeByName(pszDataTypeName: String): Int = {
    gdal.GetDataTypeByName(pszDataTypeName)
  }

  def getColorInterpretationName(eColorInterp: Int): String = {
    gdal.GetColorInterpretationName(eColorInterp)
  }

  def getPaletteInterpretationName(ePaletteInterp: Int): String = {
    gdal.GetPaletteInterpretationName(ePaletteInterp)
  }

  def decToDMS(dfAngle: Double, pszAxis: String, nPrecision: Int): String = {
    gdal.DecToDMS(dfAngle, pszAxis, nPrecision)
  }

  def decToDMS(dfAngle: Double, pszAxis: String): String = {
    gdal.DecToDMS(dfAngle, pszAxis)
  }

  def packedDMSToDec(dfPacked: Double): Double = {
    gdal.PackedDMSToDec(dfPacked)
  }

  def decToPackedDMS(dfDec: Double): Double = {
    gdal.DecToPackedDMS(dfDec)
  }

  def parseXMLString(pszXMLString: String): XMLNode = {
    gdal.ParseXMLString(pszXMLString)
  }

  def serializeXMLTree(xmlnode: XMLNode): String = {
    gdal.SerializeXMLTree(xmlnode)
  }

  def getJPEG2000StructureAsString(pszFilename: String): String = {
    gdal.GetJPEG2000StructureAsString(pszFilename)
  }

  def getJPEG2000StructureAsString(pszFilename: String, options: Vector[_]): String = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    gdal.GetJPEG2000StructureAsString(pszFilename, vector)
  }

  def getDriverCount: Int = {
    gdal.GetDriverCount
  }

  def getDriverByName(name: String): GDALDriver = {
    GDALDriver(gdal.GetDriverByName(name))
  }

  def getDriver(i: Int): GDALDriver = {
    GDALDriver(gdal.GetDriver(i))
  }

  def open(utf8_path: String, eAccess: Int): GDALDataset = AnyRef.synchronized {
    GDALDataset(gdal.Open(utf8_path, eAccess))
  }

  def open(name: String): GDALDataset = AnyRef.synchronized {
    GDALDataset(gdal.Open(name))
  }

  def openEx(utf8_path: String, nOpenFlags: Long, allowed_drivers: Vector[_], open_options: Vector[_], sibling_files: Vector[_]): GDALDataset = AnyRef.synchronized {
    val ad = new java.util.Vector[Any]()
    ad.addAll(allowed_drivers.asJavaCollection)

    val oo = new java.util.Vector[Any]()
    oo.addAll(open_options.asJavaCollection)

    val sf = new java.util.Vector[Any]()
    sf.addAll(sibling_files.asJavaCollection)

    GDALDataset(gdal.OpenEx(utf8_path, nOpenFlags, ad, oo, sf))
  }

  def openEx(utf8_path: String, nOpenFlags: Long, allowed_drivers: Vector[_], open_options: Vector[_]): GDALDataset = AnyRef.synchronized {
    val ad = new java.util.Vector[Any]()
    ad.addAll(allowed_drivers.asJavaCollection)

    val oo = new java.util.Vector[Any]()
    oo.addAll(open_options.asJavaCollection)

    GDALDataset(gdal.OpenEx(utf8_path, nOpenFlags, ad, oo))
  }

  def openEx(utf8_path: String, nOpenFlags: Long, allowed_drivers: Vector[_]): GDALDataset = AnyRef.synchronized {
    val ad = new java.util.Vector[Any]()
    ad.addAll(allowed_drivers.asJavaCollection)

    GDALDataset(gdal.OpenEx(utf8_path, nOpenFlags, ad))
  }

  def openEx(utf8_path: String, nOpenFlags: Long): GDALDataset = AnyRef.synchronized {
    GDALDataset(gdal.OpenEx(utf8_path, nOpenFlags))
  }

  def openEx(utf8_path: String): GDALDataset = AnyRef.synchronized {
    GDALDataset(gdal.OpenEx(utf8_path))
  }

  def openShared(utf8_path: String, eAccess: Int): GDALDataset = {
    GDALDataset(gdal.OpenShared(utf8_path, eAccess))
  }

  def openShared(utf8_path: String): GDALDataset = {
    GDALDataset(gdal.OpenShared(utf8_path))
  }

  def identifyDriver(utf8_path: String, papszSiblings: Vector[_]): GDALDriver = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(papszSiblings.asJavaCollection)

    GDALDriver(gdal.IdentifyDriver(utf8_path, vector))
  }

  def identifyDriver(utf8_path: String): GDALDriver = {
    GDALDriver(gdal.IdentifyDriver(utf8_path))
  }

  def identifyDriverEx(utf8_path: String, nIdentifyFlags: Long, allowed_drivers: Vector[_], sibling_files: Vector[_]): GDALDriver = {
    val ad = new java.util.Vector[Any]()
    ad.addAll(allowed_drivers.asJavaCollection)

    val sf = new java.util.Vector[Any]()
    sf.addAll(sibling_files.asJavaCollection)

    GDALDriver(gdal.IdentifyDriverEx(utf8_path, nIdentifyFlags, ad, sf))
  }

  def identifyDriverEx(utf8_path: String, nIdentifyFlags: Long, allowed_drivers: Vector[_]): GDALDriver = {
    val ad = new java.util.Vector[Any]()
    ad.addAll(allowed_drivers.asJavaCollection)

    GDALDriver(gdal.IdentifyDriverEx(utf8_path, nIdentifyFlags, ad))
  }

  def identifyDriverEx(utf8_path: String, nIdentifyFlags: Long): GDALDriver = {
    GDALDriver(gdal.IdentifyDriverEx(utf8_path, nIdentifyFlags))
  }

  def identifyDriverEx(utf8_path: String): GDALDriver = {
    GDALDriver(gdal.IdentifyDriverEx(utf8_path))
  }

  def generalCmdLineProcessor(papszArgv: Vector[_], nOptions: Int): Vector[String] = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(papszArgv.asJavaCollection)

    gdal.GeneralCmdLineProcessor(vector, nOptions).asScala.toVector.map(_.asInstanceOf[String])
  }

  def generalCmdLineProcessor(papszArgv: Vector[_]): Vector[String] = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(papszArgv.asJavaCollection)

    gdal.GeneralCmdLineProcessor(vector).asScala.toVector.map(_.asInstanceOf[String])
  }

  def GDALInfo(hDataset: GDALDataset, infoOptions: InfoOptions): String = {
    gdal.GDALInfo(hDataset.underlying, infoOptions)
  }

  def translate(dest: String, dataset: GDALDataset, translateOptions: TranslateOptions, callback: ProgressCallback): GDALDataset = AnyRef.synchronized {
    GDALDataset(gdal.Translate(dest, dataset.underlying, translateOptions, callback))
  }

  def translate(dest: String, dataset: GDALDataset, translateOptions: TranslateOptions): GDALDataset = AnyRef.synchronized {
    GDALDataset(gdal.Translate(dest, dataset.underlying, translateOptions))
  }

  def warp(dstDS: GDALDataset, object_list_count: Array[GDALDataset], warpAppOptions: WarpOptions, callback: ProgressCallback): Int = AnyRef.synchronized {
    gdal.Warp(dstDS.underlying, object_list_count.map(_.underlying), warpAppOptions, callback)
  }

  def warp(dstDS: GDALDataset, object_list_count: Array[GDALDataset], warpAppOptions: WarpOptions): Int = {
    gdal.Warp(dstDS.underlying, object_list_count.map(_.underlying), warpAppOptions)
  }

  def warp(dest: String, object_list_count: Array[GDALDataset], warpAppOptions: WarpOptions, callback: ProgressCallback): GDALDataset = AnyRef.synchronized {
    GDALDataset(gdal.Warp(dest, object_list_count.map(_.underlying), warpAppOptions, callback))
  }

  def warp(dest: String, object_list_count: Array[GDALDataset], warpAppOptions: WarpOptions): GDALDataset = AnyRef.synchronized {
    GDALDataset(gdal.Warp(dest, object_list_count.map(_.underlying), warpAppOptions))
  }

  def vectorTranslate(dstDS: GDALDataset, srcDS: GDALDataset, options: VectorTranslateOptions, callback: ProgressCallback): Int = {
    gdal.VectorTranslate(dstDS.underlying, srcDS.underlying, options, callback)
  }

  def vectorTranslate(dstDS: GDALDataset, srcDS: GDALDataset, options: VectorTranslateOptions): Int = {
    gdal.VectorTranslate(dstDS.underlying, srcDS.underlying, options)
  }

  def vectorTranslate(dest: String, srcDS: GDALDataset, options: VectorTranslateOptions, callback: ProgressCallback): GDALDataset = {
    GDALDataset(gdal.VectorTranslate(dest, srcDS.underlying, options, callback))
  }

  def vectorTranslate(dest: String, srcDS: GDALDataset, options: VectorTranslateOptions): GDALDataset = {
    GDALDataset(gdal.VectorTranslate(dest, srcDS.underlying, options))
  }

  def DEMProcessing(dest: String, dataset: GDALDataset, pszProcessing: String, pszColorFilename: String, options: DEMProcessingOptions, callback: ProgressCallback): GDALDataset = {
    GDALDataset(gdal.DEMProcessing(dest, dataset.underlying, pszProcessing, pszColorFilename, options, callback))
  }

  def DEMProcessing(dest: String, dataset: GDALDataset, pszProcessing: String, pszColorFilename: String, options: DEMProcessingOptions): GDALDataset = {
    GDALDataset(gdal.DEMProcessing(dest, dataset.underlying, pszProcessing, pszColorFilename, options))
  }

  def nearblack(dstDS: GDALDataset, srcDS: GDALDataset, options: NearblackOptions, callback: ProgressCallback): Int = {
    gdal.Nearblack(dstDS.underlying, srcDS.underlying, options, callback)
  }

  def nearblack(dstDS: GDALDataset, srcDS: GDALDataset, options: NearblackOptions): Int = {
    gdal.Nearblack(dstDS.underlying, srcDS.underlying, options)
  }

  def nearblack(dest: String, srcDS: GDALDataset, options: NearblackOptions, callback: ProgressCallback): GDALDataset = {
    GDALDataset(gdal.Nearblack(dest, srcDS.underlying, options, callback))
  }

  def nearblack(dest: String, srcDS: GDALDataset, options: NearblackOptions): GDALDataset = {
    GDALDataset(gdal.Nearblack(dest, srcDS.underlying, options))
  }

  def grid(dest: String, dataset: GDALDataset, options: GridOptions, callback: ProgressCallback): GDALDataset = {
    GDALDataset(gdal.Grid(dest, dataset.underlying, options, callback))
  }

  def grid(dest: String, dataset: GDALDataset, options: GridOptions): GDALDataset = {
    GDALDataset(gdal.Grid(dest, dataset.underlying, options))
  }

  def rasterize(dstDS: GDALDataset, srcDS: GDALDataset, options: RasterizeOptions, callback: ProgressCallback): Int = {
    gdal.Rasterize(dstDS.underlying, srcDS.underlying, options, callback)
  }

  def rasterize(dstDS: GDALDataset, srcDS: GDALDataset, options: RasterizeOptions): Int = {
    gdal.Rasterize(dstDS.underlying, srcDS.underlying, options)
  }

  def rasterize(dest: String, srcDS: GDALDataset, options: RasterizeOptions, callback: ProgressCallback): GDALDataset = {
    GDALDataset(gdal.Rasterize(dest, srcDS.underlying, options, callback))
  }

  def rasterize(dest: String, srcDS: GDALDataset, options: RasterizeOptions): GDALDataset = {
    GDALDataset(gdal.Rasterize(dest, srcDS.underlying, options))
  }

  def buildVRT(dest: String, object_list_count: Array[GDALDataset], options: BuildVRTOptions, callback: ProgressCallback): GDALDataset = {
    GDALDataset(gdal.BuildVRT(dest, object_list_count.map(_.underlying), options, callback))
  }

  def buildVRT(dest: String, object_list_count: Array[GDALDataset], options: BuildVRTOptions): GDALDataset = {
    GDALDataset(gdal.BuildVRT(dest, object_list_count.map(_.underlying), options))
  }

  def buildVRT(dest: String, source_filenames: Vector[_], options: BuildVRTOptions, callback: ProgressCallback): GDALDataset = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(source_filenames.asJavaCollection)
    GDALDataset(gdal.BuildVRT(dest, vector, options, callback))
  }

  def buildVRT(dest: String, source_filenames: Vector[_], options: BuildVRTOptions): GDALDataset = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(source_filenames.asJavaCollection)
    GDALDataset(gdal.BuildVRT(dest, vector, options))
  }
}
