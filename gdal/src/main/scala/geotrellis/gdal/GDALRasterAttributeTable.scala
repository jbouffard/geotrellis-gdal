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

import org.gdal.gdal.RasterAttributeTable

case class GDALRasterAttributeTable(underlying: RasterAttributeTable) extends Cloneable {
  override def clone: GDALRasterAttributeTable = GDALRasterAttributeTable(underlying.Clone)

  def getColumnCount: Int = {
    underlying.GetColumnCount
  }

  def getNameOfCol(iCol: Int): String = {
    underlying.GetNameOfCol(iCol)
  }

  def getUsageOfCol(iCol: Int): Int = {
    underlying.GetUsageOfCol(iCol)
  }

  def getTypeOfCol(iCol: Int): Int = {
    underlying.GetTypeOfCol(iCol)
  }

  def getColOfUsage(eUsage: Int): Int = {
    underlying.GetColOfUsage(eUsage)
  }

  def getRowCount: Int = {
    underlying.GetRowCount
  }

  def getValueAsString(iRow: Int, iCol: Int): String = {
    underlying.GetValueAsString(iRow, iCol)
  }

  def getValueAsInt(iRow: Int, iCol: Int): Int = {
    underlying.GetValueAsInt(iRow, iCol)
  }

  def getValueAsDouble(iRow: Int, iCol: Int): Double = {
    underlying.GetValueAsDouble(iRow, iCol)
  }

  def setValueAsString(iRow: Int, iCol: Int, pszValue: String): Unit = {
    underlying.SetValueAsString(iRow, iCol, pszValue)
  }

  def setValueAsInt(iRow: Int, iCol: Int, nValue: Int): Unit = {
    underlying.SetValueAsInt(iRow, iCol, nValue)
  }

  def setValueAsDouble(iRow: Int, iCol: Int, dfValue: Double): Unit = {
    underlying.SetValueAsDouble(iRow, iCol, dfValue)
  }

  def setRowCount(nCount: Int): Unit = {
    underlying.SetRowCount(nCount)
  }

  def createColumn(pszName: String, eType: Int, eUsage: Int): Int = {
    underlying.CreateColumn(pszName, eType, eUsage)
  }

  def getLinearBinning(pdfRow0Min: Array[Double], pdfBinSize: Array[Double]): Boolean = {
    underlying.GetLinearBinning(pdfRow0Min, pdfBinSize)
  }

  def setLinearBinning(dfRow0Min: Double, dfBinSize: Double): Int = {
    underlying.SetLinearBinning(dfRow0Min, dfBinSize)
  }

  def getRowOfValue(dfValue: Double): Int = {
    underlying.GetRowOfValue(dfValue)
  }

  def changesAreWrittenToFile: Int = {
    underlying.ChangesAreWrittenToFile
  }

  def dumpReadable: Unit = {
    underlying.DumpReadable
  }

  def delete: Unit = {
    if(underlying != null) underlying.delete
  }

  override protected def finalize(): Unit = {
    if(underlying != null) underlying.delete
    super.finalize()
  }
}
