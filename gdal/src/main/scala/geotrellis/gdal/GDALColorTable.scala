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

import java.awt.Color
import java.awt.image.IndexColorModel

import org.gdal.gdal.{ColorTable, gdal}

case class GDALColorTable(underlying: ColorTable) extends Cloneable {
  override def clone: GDALColorTable = {
    GDALColorTable(underlying.Clone)
  }

  def getIndexColorModel(bits: Int): IndexColorModel = {
    underlying.getIndexColorModel(bits)
  }

  def getPaletteInterpretation: Int = {
    underlying.GetPaletteInterpretation
  }

  def getPaletteInterpretationName: String = {
    gdal.GetPaletteInterpretationName(getPaletteInterpretation)
  }

  def getCount: Int = {
    underlying.GetCount
  }

  def getColorEntry(entry: Int): Color = {
    underlying.GetColorEntry(entry)
  }

  def setColorEntry(entry: Int, centry: Color): Unit = {
    underlying.SetColorEntry(entry, centry)
  }

  def createColorRamp(nStartIndex: Int, startcolor: Color, nEndIndex: Int, endcolor: Color): Unit = {
    underlying.CreateColorRamp(nStartIndex, startcolor, nEndIndex, endcolor)
  }

  def delete: Unit = {
    if(underlying != null) underlying.delete
  }

  override protected def finalize(): Unit = {
    if(underlying != null) underlying.delete
    super.finalize()
  }
}
