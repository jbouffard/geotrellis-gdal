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

import spire.syntax.cfor._
import org.gdal.gdal.Dataset
import org.gdal.gdal.gdal
import org.gdal.gdalconst.gdalconstConstants

import java.nio.ByteBuffer
import java.nio.ByteOrder


case class GdalReader(rasterDataSet: RasterDataSet) {
  private lazy val dataset: Dataset = rasterDataSet.ds

  def read(extent: Extent): Raster[MultibandTile] =
    read(rasterDataSet.rasterExtent.gridBoundsFor(extent))

  def read(
    gridBounds: GridBounds = rasterDataSet.gridBounds,
    bands: Seq[Int] = 0 until rasterDataSet.bandCount,
    noDataValue: Option[Double] = rasterDataSet.noDataValue
  ): Raster[MultibandTile] = {
    // NOTE: Bands are not 0-base indexed, so we must add 1// NOTE: Bands are not 0-base indexed, so we must add 1
    val baseBand = dataset.GetRasterBand(1)

    val bandCount = bands.size
    val indexBand = bands.zipWithIndex.map { case (v, i) => (i, v) }.toMap

    // setting buffer properties
    val pixelCount = rasterDataSet.cols * rasterDataSet.rows
    // sampleFormat
    val bufferType = baseBand.getDataType
    // samples per pixel
    val samplesPerPixel = dataset.getRasterCount
    // bits per sample
    val typeSizeInBits = gdal.GetDataTypeSize(bufferType)
    val typeSizeInBytes = gdal.GetDataTypeSize(bufferType) / 8
    val bufferSize = bandCount * pixelCount * typeSizeInBytes

    val multibandTile =
      /** TODO: think about how to handle UByte case **/
      if (bufferType == gdalconstConstants.GDT_Byte) {
        // in the byte case we can strictly use
        val bandsDataArray = Array.ofDim[Array[Byte]](bandCount)
        cfor(0)(_ < bandCount, _ + 1) { i =>
          val rBand = dataset.GetRasterBand(indexBand(i) + 1)
          val dataBuffer = new Array[Byte](bufferSize.toInt)
          val returnVal = rBand.ReadRaster(
            gridBounds.colMin,
            gridBounds.rowMin,
            gridBounds.width,
            gridBounds.height,
            gridBounds.width,
            gridBounds.height,
            bufferType,
            dataBuffer
          )

          if(returnVal != gdalconstConstants.CE_None)
            throw new Exception("An error happened during the GDAL Read.")

          bandsDataArray(i) = dataBuffer
        }

        if(typeSizeInBits == 1) {
          MultibandTile(bandsDataArray.map { b => BitArrayTile(b, gridBounds.width, gridBounds.height) })
        } else {
          val ct = noDataValue match {
            case Some(nd) => ByteUserDefinedNoDataCellType(nd.toByte)
            case _ => ByteCellType
          }
          MultibandTile(bandsDataArray.map { b => ByteArrayTile(b, gridBounds.width, gridBounds.height, ct) })
        }
        } else {
          // for these types we need buffers
          val bandsDataBuffer = Array.ofDim[ByteBuffer](bandCount)
          cfor(0)(_ < bandCount, _ + 1) { i =>
            val rBand = dataset.GetRasterBand(indexBand(i) + 1)
            val dataBuffer = new Array[Byte](bufferSize.toInt)
            val returnVal = rBand.ReadRaster(
              gridBounds.colMin,
              gridBounds.rowMin,
              gridBounds.width,
              gridBounds.height,
              gridBounds.width,
              gridBounds.height,
              bufferType,
              dataBuffer
            )

            if(returnVal != gdalconstConstants.CE_None)
              throw new Exception("An error happened during the GDAL Read.")

            bandsDataBuffer(i) = ByteBuffer.wrap(dataBuffer,0, dataBuffer.length)
          }

          if (bufferType == gdalconstConstants.GDT_Int16 || bufferType == gdalconstConstants.GDT_UInt16) {
            val shorts = new Array[Array[Short]](bandCount)
            cfor(0)(_ < bandCount, _ + 1) { i =>
              shorts(i) = new Array[Short](pixelCount)
              bandsDataBuffer(i).order(ByteOrder.nativeOrder)
              bandsDataBuffer(i).asShortBuffer().get(shorts(i), 0, pixelCount)
            }

            if (bufferType == gdalconstConstants.GDT_Int16) {
              val ct = noDataValue match {
                case Some(nd) => ShortUserDefinedNoDataCellType(nd.toShort)
                case _ => ShortConstantNoDataCellType
              }
              MultibandTile(shorts.map(ShortArrayTile(_, gridBounds.width, gridBounds.height, ct)))
            } else {
              val ct = noDataValue match {
                case Some(nd) => UShortUserDefinedNoDataCellType(nd.toShort)
                case _ => UShortConstantNoDataCellType
              }
              MultibandTile(shorts.map(UShortArrayTile(_, gridBounds.width, gridBounds.height, ct)))
            }
            } else if (bufferType == gdalconstConstants.GDT_Int32 || bufferType == gdalconstConstants.GDT_UInt32) {
              val ct = noDataValue match {
                case Some(nd) => IntUserDefinedNoDataCellType(nd.toInt)
                case _ => IntConstantNoDataCellType
              }

              val ints = new Array[Array[Int]](bandCount)
              cfor(0)(_ < bandCount, _ + 1) { i =>
                ints(i) = new Array[Int](pixelCount)
                bandsDataBuffer(i).order(ByteOrder.nativeOrder)
                bandsDataBuffer(i).asIntBuffer().get(ints(i), 0, pixelCount)
              }

              MultibandTile(ints.map(IntArrayTile(_, gridBounds.width, gridBounds.height, ct)))
            } else if (bufferType == gdalconstConstants.GDT_Float32) {
              val ct = noDataValue match {
                case Some(nd) => FloatUserDefinedNoDataCellType(nd.toFloat)
                case _ => FloatConstantNoDataCellType
              }

              val floats = new Array[Array[Float]](bandCount)
              cfor(0)(_ < bandCount, _ + 1) { i =>
                floats(i) = new Array[Float](pixelCount)
                bandsDataBuffer(i).order(ByteOrder.nativeOrder)
                bandsDataBuffer(i).asFloatBuffer().get(floats(i), 0, pixelCount)
              }

              MultibandTile(floats.map(FloatArrayTile(_, gridBounds.width, gridBounds.height, ct)))
            } else if (bufferType == gdalconstConstants.GDT_Float64) {
              val ct = noDataValue match {
                case Some(nd) => DoubleUserDefinedNoDataCellType(nd)
                case _ => DoubleConstantNoDataCellType
              }

              val doubles = new Array[Array[Double]](bandCount)
              cfor(0)(_ < bandCount, _ + 1) { i =>
                doubles(i) = new Array[Double](pixelCount)
                bandsDataBuffer(i).order(ByteOrder.nativeOrder)
                bandsDataBuffer(i).asDoubleBuffer().get(doubles(i), 0, pixelCount)
              }

              MultibandTile(doubles.map(DoubleArrayTile(_, gridBounds.width, gridBounds.height, ct)))
            } else
              throw new Exception(s"The specified data type is actually unsupported: $bufferType")
        }

    Raster(multibandTile, rasterDataSet.extent)
  }
}


object GdalReader {
  def apply(path: String): GdalReader =
    GdalReader(Gdal.open(path))
}
