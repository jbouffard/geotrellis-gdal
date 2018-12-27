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

import org.gdal.ogr._

import scala.collection.JavaConverters._

case class GDALLayer(underlying: Layer) {
  def getExtent(force: Boolean): Array[Double] = {
    underlying.GetExtent(force)
  }

  def getExtent: Array[Double] = {
    underlying.GetExtent
  }

  def getRefCount: Int = {
    underlying.GetRefCount
  }

  def setSpatialFilter(filter: Geometry): Unit = {
    underlying.SetSpatialFilter(filter)
  }

  def setSpatialFilterRect(minx: Double, miny: Double, maxx: Double, maxy: Double): Unit = {
    underlying.SetSpatialFilterRect(minx, miny, maxx, maxy)
  }

  def setSpatialFilter(iGeomField: Int, filter: Geometry): Unit = {
    underlying.SetSpatialFilter(iGeomField, filter)
  }

  def setSpatialFilterRect(iGeomField: Int, minx: Double, miny: Double, maxx: Double, maxy: Double): Unit = {
    underlying.SetSpatialFilterRect(iGeomField, minx, miny, maxx, maxy)
  }

  def getSpatialFilter: Geometry = {
    underlying.GetSpatialFilter
  }

  def setAttributeFilter(filter_string: String): Int = {
    underlying.SetAttributeFilter(filter_string)
  }

  def resetReading(): Unit = {
    underlying.ResetReading
  }

  def getName: String = {
    underlying.GetName
  }

  def getGeomType: Int = {
    underlying.GetGeomType
  }

  def getGeometryColumn: String = {
    underlying.GetGeometryColumn
  }

  def getFIDColumn: String = {
    underlying.GetFIDColumn
  }

  def getFeature(fid: Long): Feature = {
    underlying.GetFeature(fid)
  }

  def getNextFeature: Feature = {
    underlying.GetNextFeature
  }

  def setNextByIndex(new_index: Long): Int = {
    underlying.SetNextByIndex(new_index)
  }

  def setFeature(feature: Feature): Int = {
    underlying.SetFeature(feature)
  }

  def createFeature(feature: Feature): Int = {
    underlying.CreateFeature(feature)
  }

  def deleteFeature(fid: Long): Int = {
    underlying.DeleteFeature(fid)
  }

  def syncToDisk: Int = {
    underlying.SyncToDisk
  }

  def getLayerDefn: FeatureDefn = {
    underlying.GetLayerDefn
  }

  def getFeatureCount(force: Int): Long = {
    underlying.GetFeatureCount(force)
  }

  def getFeatureCount: Long = {
    underlying.GetFeatureCount
  }

  def getExtent(argout: Array[Double], force: Int): Int = {
    underlying.GetExtent(argout, force)
  }

  def testCapability(cap: String): Boolean = {
    underlying.TestCapability(cap)
  }

  def createField(field_def: FieldDefn, approx_ok: Int): Int = {
    underlying.CreateField(field_def, approx_ok)
  }

  def createField(field_def: FieldDefn): Int = {
    underlying.CreateField(field_def)
  }

  def deleteField(iField: Int): Int = {
    underlying.DeleteField(iField)
  }

  def reorderField(iOldFieldPos: Int, iNewFieldPos: Int): Int = {
    underlying.ReorderField(iOldFieldPos, iNewFieldPos)
  }

  def reorderFields(nList: Array[Int]): Int = {
    underlying.ReorderFields(nList)
  }

  def alterFieldDefn(iField: Int, field_def: FieldDefn, nFlags: Int): Int = {
    underlying.AlterFieldDefn(iField, field_def, nFlags)
  }

  def createGeomField(field_def: GeomFieldDefn, approx_ok: Int): Int = {
    underlying.CreateGeomField(field_def, approx_ok)
  }

  def createGeomField(field_def: GeomFieldDefn): Int = {
    underlying.CreateGeomField(field_def)
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

  def findFieldIndex(pszFieldName: String, bExactMatch: Int): Int = {
    underlying.FindFieldIndex(pszFieldName, bExactMatch)
  }

  def getSpatialRef: OSRSpatialReference = {
    OSRSpatialReference(underlying.GetSpatialRef)
  }

  def getFeaturesRead: Long = {
    underlying.GetFeaturesRead
  }

  def setIgnoredFields(options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.SetIgnoredFields(vector)
  }

  def intersection(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Intersection(method_layer.underlying, result_layer.underlying, vector, callback)
  }

  def intersection(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Intersection(method_layer.underlying, result_layer.underlying, vector)
  }

  def intersection(method_layer: GDALLayer, result_layer: GDALLayer): Int = {
    underlying.Intersection(method_layer.underlying, result_layer.underlying)
  }

  def union(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Union(method_layer.underlying, result_layer.underlying, vector, callback)
  }

  def union(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Union(method_layer.underlying, result_layer.underlying, vector)
  }

  def union(method_layer: GDALLayer, result_layer: GDALLayer): Int = {
    underlying.Union(method_layer.underlying, result_layer.underlying)
  }

  def symDifference(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.SymDifference(method_layer.underlying, result_layer.underlying, vector, callback)
  }

  def symDifference(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.SymDifference(method_layer.underlying, result_layer.underlying, vector)
  }

  def symDifference(method_layer: GDALLayer, result_layer: GDALLayer): Int = {
    underlying.SymDifference(method_layer.underlying, result_layer.underlying)
  }

  def identity(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Identity(method_layer.underlying, result_layer.underlying, vector, callback)
  }

  def identity(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Identity(method_layer.underlying, result_layer.underlying, vector)
  }

  def identity(method_layer: GDALLayer, result_layer: GDALLayer): Int = {
    underlying.Identity(method_layer.underlying, result_layer.underlying)
  }

  def update(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Update(method_layer.underlying, result_layer.underlying, vector, callback)
  }

  def update(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Update(method_layer.underlying, result_layer.underlying, vector)
  }

  def update(method_layer: GDALLayer, result_layer: GDALLayer): Int = {
    underlying.Update(method_layer.underlying, result_layer.underlying)
  }

  def clip(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Clip(method_layer.underlying, result_layer.underlying, vector, callback)
  }

  def clip(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Clip(method_layer.underlying, result_layer.underlying, vector)
  }

  def clip(method_layer: GDALLayer, result_layer: GDALLayer): Int = {
    underlying.Clip(method_layer.underlying, result_layer.underlying)
  }

  def erase(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_], callback: ProgressCallback): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Erase(method_layer.underlying, result_layer.underlying, vector, callback)
  }

  def erase(method_layer: GDALLayer, result_layer: GDALLayer, options: Vector[_]): Int = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    underlying.Erase(method_layer.underlying, result_layer.underlying, vector)
  }

  def erase(method_layer: GDALLayer, result_layer: GDALLayer): Int = {
    underlying.Erase(method_layer.underlying, result_layer.underlying)
  }

  def getStyleTable: GDALStyleTable = {
    GDALStyleTable(underlying.GetStyleTable)
  }

  def setStyleTable(table: GDALStyleTable): Unit = {
    underlying.SetStyleTable(table.underlying)
  }

  def delete: Unit = {
    if(underlying != null) underlying.delete
  }
}
