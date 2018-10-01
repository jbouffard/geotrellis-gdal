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

import org.gdal.gdal.gdal

object GdalDataType {
  val types =
    List(
      TypeUnknown, TypeByte, TypeUInt16, TypeInt16, TypeUInt32,
      TypeInt32, TypeFloat32, TypeFloat64, TypeCInt16,
      TypeCInt32, TypeCFloat32, TypeCFloat64
    )

  implicit def intToGdalDataType(i: Int): GdalDataType =
    types.find(_.code == i) match {
      case Some(dt) => dt
      case None => sys.error(s"Invalid GDAL data type code: $i")
    }

  implicit def GdalDataTypeToInt(typ: GdalDataType): Int =
    typ.code
}

abstract sealed class GdalDataType(val code: Int) {
  override
  def toString: String = gdal.GetDataTypeName(code)
}

case object TypeUnknown extends GdalDataType(0)
case object TypeByte extends GdalDataType(1)
case object TypeUInt16 extends GdalDataType(2)
case object TypeInt16 extends GdalDataType(3)
case object TypeUInt32 extends GdalDataType(4)
case object TypeInt32 extends GdalDataType(5)
case object TypeFloat32 extends GdalDataType(6)
case object TypeFloat64 extends GdalDataType(7)
case object TypeCInt16 extends GdalDataType(8)
case object TypeCInt32 extends GdalDataType(9)
case object TypeCFloat32 extends GdalDataType(10)
case object TypeCFloat64 extends GdalDataType(11)
