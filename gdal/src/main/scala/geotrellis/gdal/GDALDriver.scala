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

import org.gdal.gdal.{Driver, ProgressCallback}

import scala.collection.JavaConverters._

case class GDALDriver(underlying: Driver) extends GDALMajorObject {
  def create(name: String, xsize: Int, ysize: Int, bands: Int, eType: Int, options: Array[String]): GDALDataset = {
    GDALDataset(underlying.Create(name, xsize, ysize, bands, eType, options))
  }

  def create(name: String, xsize: Int, ysize: Int, bands: Int, options: Array[String]): GDALDataset = {
    GDALDataset(underlying.Create(name, xsize, ysize, bands, options))
  }

  def createCopy(name: String, src: GDALDataset, strict: Int, options: Array[String]): GDALDataset = {
    GDALDataset(underlying.CreateCopy(name, src.underlying, strict, options))
  }

  def createCopy(name: String, src: GDALDataset, options: Vector[_]): GDALDataset = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    GDALDataset(underlying.CreateCopy(name, src.underlying, vector))
  }

  def createCopy(name: String, src: GDALDataset, options: Array[String]): GDALDataset = {
    GDALDataset(underlying.CreateCopy(name, src.underlying, options))
  }

  def getShortName: String = {
    underlying.getShortName
  }

  def getLongName: String = {
    underlying.getLongName
  }

  def getHelpTopic: String = {
    underlying.getHelpTopic
  }

  def create(utf8_path: String, xsize: Int, ysize: Int, bands: Int, eType: Int, options: Vector[_]): GDALDataset = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    GDALDataset(underlying.Create(utf8_path, xsize, ysize, bands, eType, vector))
  }

  def create(utf8_path: String, xsize: Int, ysize: Int, bands: Int, eType: Int): GDALDataset = {
    GDALDataset(underlying.Create(utf8_path, xsize, ysize, bands, eType))
  }

  def create(utf8_path: String, xsize: Int, ysize: Int, bands: Int): GDALDataset = {
    GDALDataset(underlying.Create(utf8_path, xsize, ysize, bands))
  }

  def create(utf8_path: String, xsize: Int, ysize: Int): GDALDataset = {
    GDALDataset(underlying.Create(utf8_path, xsize, ysize))
  }

  def createCopy(utf8_path: String, src: GDALDataset, strict: Int, options: Vector[_], callback: ProgressCallback): GDALDataset = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    GDALDataset(underlying.CreateCopy(utf8_path, src.underlying, strict, vector, callback))
  }

  def createCopy(utf8_path: String, src: GDALDataset, strict: Int, options: Vector[_]): GDALDataset = {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    GDALDataset(underlying.CreateCopy(utf8_path, src.underlying, strict, vector))
  }

  def createCopy(utf8_path: String, src: GDALDataset, strict: Int): GDALDataset = {
    GDALDataset(underlying.CreateCopy(utf8_path, src.underlying, strict))
  }

  def createCopy(utf8_path: String, src: GDALDataset): GDALDataset = {
    GDALDataset(underlying.CreateCopy(utf8_path, src.underlying))
  }

  def delete(utf8_path: String): Int = {
    underlying.Delete(utf8_path)
  }

  def rename(newName: String, oldName: String): Int = {
    underlying.Rename(newName, oldName)
  }

  def copyFiles(newName: String, oldName: String): Int = {
    underlying.CopyFiles(newName, oldName)
  }

  def register: Int = {
    underlying.Register
  }

  def deregister(): Unit = {
    underlying.Deregister
  }

  def delete: Unit = {
    if(underlying != null) underlying.delete
  }
}
