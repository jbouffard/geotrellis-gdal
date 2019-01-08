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

package geotrellis.gdal.osr

import geotrellis.proj4.CRS
import org.gdal.osr.SpatialReference

import scala.collection.JavaConverters._

/**
  * Original OSR objects can't be used in a multithreaded environment
  *
  * http://osgeo-org.1560.x6.nabble.com/gdal-dev-Possible-concurrency-issue-in-OGRCoordinateTransformation-td5285641.html
  */
case class OSRSpatialReference(underlying: SpatialReference) extends Cloneable {
  override protected def finalize(): Unit = {
    if(underlying != null) AnyRef.synchronized(underlying.delete)
    super.finalize()
  }

  def delete: Unit = {
    if(underlying != null) AnyRef.synchronized(underlying.delete)
  }

  override def clone: OSRSpatialReference = OSRSpatialReference(underlying.Clone)

  def exportToWkt: String = AnyRef.synchronized {
    underlying.ExportToWkt()
  }

  def exportToPrettyWkt(simplify: Int): String = AnyRef.synchronized {
    underlying.ExportToPrettyWkt(simplify)
  }

  def exportToPrettyWkt: String = AnyRef.synchronized {
    underlying.ExportToPrettyWkt
  }

  def exportToProj4: String = AnyRef.synchronized {
    underlying.ExportToProj4
  }

  def exportToXML(dialect: String): String = AnyRef.synchronized {
    underlying.ExportToXML(dialect)
  }

  def exportToXML: String = AnyRef.synchronized {
    underlying.ExportToXML
  }

  def exportToMICoordSys: String = AnyRef.synchronized {
    underlying.ExportToMICoordSys
  }

  def getTOWGS84: Array[Double] = AnyRef.synchronized {
    underlying.GetTOWGS84
  }

  def setTOWGS84(p1: Double, p2: Double, p3: Double): Int = AnyRef.synchronized {
    underlying.SetTOWGS84(p1, p2, p3)
  }

  override def toString = AnyRef.synchronized(underlying.toString)

  def isSame(rhs: OSRSpatialReference): Int = AnyRef.synchronized {
    underlying.IsSame(rhs.underlying)
  }

  def isSameGeogCS(rhs: OSRSpatialReference): Int = AnyRef.synchronized {
    underlying.IsSameGeogCS(rhs.underlying)
  }

  def isSameVertCS(rhs: OSRSpatialReference): Int = AnyRef.synchronized {
    underlying.IsSameVertCS(rhs.underlying)
  }

  def isGeographic: Int = AnyRef.synchronized {
    underlying.IsGeographic
  }

  def isProjected: Int = AnyRef.synchronized {
    underlying.IsProjected
  }

  def isCompound: Int = AnyRef.synchronized {
    underlying.IsCompound
  }

  def isGeocentric: Int = AnyRef.synchronized {
    underlying.IsGeocentric
  }

  def isLocal: Int = AnyRef.synchronized {
    underlying.IsLocal
  }

  def isVertical: Int = AnyRef.synchronized {
    underlying.IsVertical
  }

  def EPSGTreatsAsLatLong: Int = AnyRef.synchronized {
    underlying.EPSGTreatsAsLatLong
  }

  def EPSGTreatsAsNorthingEasting: Int = AnyRef.synchronized {
    underlying.EPSGTreatsAsNorthingEasting
  }

  def setAuthority(pszTargetKey: String, pszAuthority: String, nCode: Int): Int = AnyRef.synchronized {
    underlying.SetAuthority(pszTargetKey, pszAuthority, nCode)
  }

  def getAttrValue(name: String, child: Int): String = AnyRef.synchronized {
    underlying.GetAttrValue(name, child)
  }

  def getAttrValue(name: String): String = AnyRef.synchronized {
    underlying.GetAttrValue(name)
  }

  def setAttrValue(name: String, value: String): Int = AnyRef.synchronized {
    underlying.SetAttrValue(name, value)
  }

  def setAngularUnits(name: String, to_radians: Double): Int = AnyRef.synchronized {
    underlying.SetAngularUnits(name, to_radians)
  }

  def getAngularUnits: Double = AnyRef.synchronized {
    underlying.GetAngularUnits
  }

  def getAngularUnitsName: String = AnyRef.synchronized {
    underlying.GetAngularUnitsName
  }

  def setTargetLinearUnits(target: String, name: String, to_meters: Double): Int = AnyRef.synchronized {
    underlying.SetTargetLinearUnits(target, name, to_meters)
  }

  def setLinearUnits(name: String, to_meters: Double): Int = AnyRef.synchronized {
    underlying.SetLinearUnits(name, to_meters)
  }

  def setLinearUnitsAndUpdateParameters(name: String, to_meters: Double): Int = AnyRef.synchronized {
    underlying.SetLinearUnitsAndUpdateParameters(name, to_meters)
  }

  def getTargetLinearUnits(target_key: String): Double = AnyRef.synchronized {
    underlying.GetTargetLinearUnits(target_key)
  }

  def getLinearUnits: Double = AnyRef.synchronized {
    underlying.GetLinearUnits
  }

  def getLinearUnitsName: String = AnyRef.synchronized {
    underlying.GetLinearUnitsName
  }

  def getAuthorityCode(target_key: String): String = AnyRef.synchronized {
    underlying.GetAuthorityCode(target_key)
  }

  def getAuthorityName(target_key: String): String = AnyRef.synchronized {
    underlying.GetAuthorityName(target_key)
  }

  def getAxisName(target_key: String, iAxis: Int): String = AnyRef.synchronized {
    underlying.GetAxisName(target_key, iAxis)
  }

  def getAxisOrientation(target_key: String, iAxis: Int): Int = AnyRef.synchronized {
    underlying.GetAxisOrientation(target_key, iAxis)
  }

  def setUTM(zone: Int, north: Int): Int = AnyRef.synchronized {
    underlying.SetUTM(zone, north)
  }

  def setUTM(zone: Int): Int = AnyRef.synchronized {
    underlying.SetUTM(zone)
  }

  def getUTMZone: Int = AnyRef.synchronized {
    underlying.GetUTMZone
  }

  def setStatePlane(zone: Int, is_nad83: Int, unitsname: String, units: Double): Int = AnyRef.synchronized {
    underlying.SetStatePlane(zone, is_nad83, unitsname, units)
  }

  def setStatePlane(zone: Int, is_nad83: Int, unitsname: String): Int = AnyRef.synchronized {
    underlying.SetStatePlane(zone, is_nad83, unitsname)
  }

  def setStatePlane(zone: Int, is_nad83: Int): Int = AnyRef.synchronized {
    underlying.SetStatePlane(zone, is_nad83)
  }

  def setStatePlane(zone: Int): Int = AnyRef.synchronized {
    underlying.SetStatePlane(zone)
  }

  def AutoIdentifyEPSG: Int = AnyRef.synchronized {
    underlying.AutoIdentifyEPSG
  }

  def setProjection(arg: String): Int = AnyRef.synchronized {
    underlying.SetProjCS(arg)
  }

  def setProjParm(name: String, value: Double): Int = AnyRef.synchronized {
    underlying.SetProjParm(name, value)
  }

  def getProjParm(name: String, default_val: Double): Double = AnyRef.synchronized {
    underlying.GetProjParm(name, default_val)
  }

  def getProjParm(name: String): Double = AnyRef.synchronized {
    underlying.GetProjParm(name)
  }

  def setNormProjParm(name: String, value: Double): Int = AnyRef.synchronized {
    underlying.SetNormProjParm(name, value)
  }

  def getNormProjParm(name: String, default_val: Double): Double = AnyRef.synchronized {
    underlying.GetNormProjParm(name, default_val)
  }

  def getNormProjParm(name: String): Double = AnyRef.synchronized {
    underlying.GetNormProjParm(name)
  }

  def getSemiMajor: Double = AnyRef.synchronized {
    underlying.GetSemiMajor
  }

  def getSemiMinor: Double = AnyRef.synchronized {
    underlying.GetSemiMinor
  }

  def getInvFlattening: Double = AnyRef.synchronized {
    underlying.GetInvFlattening
  }

  def setACEA(stdp1: Double, stdp2: Double, clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetACEA(stdp1, stdp2, clat, clong, fe, fn)
  }

  def setAE(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetAE(clat, clong, fe, fn)
  }

  def setBonne(stdp: Double, cm: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetBonne(stdp, cm, fe, fn)
  }

  def setCEA(stdp1: Double, cm: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetCEA(stdp1, cm, fe, fn)
  }

  def setCS(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetCS(clat, clong, fe, fn)
  }

  def setEC(stdp1: Double, stdp2: Double, clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetEC(stdp1, stdp2, clat, clong, fe, fn)
  }

  def setEckertIV(cm: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetEckertIV(cm, fe, fn)
  }

  def setEckertVI(cm: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetEckertIV(cm, fe, fn)
  }

  def setEquirectangular(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetEquirectangular(clat, clong, fe, fn)
  }

  def setEquirectangular2(clat: Double, clong: Double, pseudostdparallellat: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetEquirectangular2(clat, clong, pseudostdparallellat, fe, fn)
  }

  def setGaussSchreiberTMercator(clat: Double, clong: Double, sc: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetGaussSchreiberTMercator(clat, clong, sc, fe, fn)
  }

  def setGS(cm: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetGS(cm, fe, fn)
  }

  def setGH(cm: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetGH(cm, fe, fn)
  }

  def setIGH: Int = AnyRef.synchronized {
    underlying.SetIGH
  }

  def setGEOS(cm: Double, satelliteheight: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetGEOS(cm, satelliteheight, fe, fn)
  }

  def setGnomonic(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetGnomonic(clat, clong, fe, fn)
  }

  def setHOM(clat: Double, clong: Double, azimuth: Double, recttoskew: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetHOM(clat, clong, azimuth, recttoskew, scale, fe, fn)
  }

  def setHOM2PNO(clat: Double, dfLat1: Double, dfLong1: Double, dfLat2: Double, dfLong2: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetHOM2PNO(clat, dfLat1, dfLong1, dfLat2, dfLong2, scale, fe, fn)
  }

  def setKrovak(clat: Double, clong: Double, azimuth: Double, pseudostdparallellat: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetKrovak(clat, clong, azimuth, pseudostdparallellat, scale, fe, fn)
  }

  def setLAEA(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetLAEA(clat, clong, fe, fn)
  }

  def setLCC(stdp1: Double, stdp2: Double, clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetLCC(stdp1, stdp2, clat, clong, fe, fn)
  }

  def setLCC1SP(clat: Double, clong: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetLCC1SP(clat, clong, scale, fe, fn)
  }

  def setLCCB(stdp1: Double, stdp2: Double, clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetLCCB(stdp1, stdp2, clat, clong, fe, fn)
  }

  def setMC(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetMC(clat, clong, fe, fn)
  }

  def setMercator(clat: Double, clong: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetMercator(clat, clong, scale, fe, fn)
  }

  def setMercator2SP(stdp1: Double, clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetMercator2SP(stdp1, clat, clong, fe, fn)
  }

  def setMollweide(cm: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetMollweide(cm, fe, fn)
  }

  def setNZMG(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetNZMG(clat, clong, fe, fn)
  }

  def setOS(dfOriginLat: Double, dfCMeridian: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetOS(dfOriginLat,dfCMeridian, scale, fe, fn)
  }

  def setOrthographic(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetOrthographic(clat, clong, fe, fn)
  }

  def setPolyconic(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetPolyconic(clat, clong, fe, fn)
  }

  def setPS(clat: Double, clong: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetPS(clat, clong, scale, fe, fn)
  }

  def setRobinson(clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetRobinson(clong, fe, fn)
  }

  def setSinusoidal(clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetSinusoidal(clong, fe, fn)
  }

  def setStereographic(clat: Double, clong: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetStereographic(clat, clong, scale, fe, fn)
  }

  def setSOC(latitudeoforigin: Double, cm: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetSOC(latitudeoforigin, cm, fe, fn)
  }

  def setTM(clat: Double, clong: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetTM(clat, clong, scale, fe, fn)
  }

  def setTMVariant(pszVariantName: String, clat: Double, clong: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetTMVariant(pszVariantName, clat, clong, scale, fe, fn)
  }

  def setTMG(clat: Double, clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetTMG(clat, clong, fe, fn)
  }

  def setTMSO(clat: Double, clong: Double, scale: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetTMSO(clat, clong, scale, fe, fn)
  }

  def setVDG(clong: Double, fe: Double, fn: Double): Int = AnyRef.synchronized {
    underlying.SetVDG(clong, fe, fn)
  }

  def setWellKnownGeogCS(name: String): Int = AnyRef.synchronized {
    underlying.SetWellKnownGeogCS(name)
  }

  def setFromUserInput(name: String): Int = AnyRef.synchronized {
    underlying.SetFromUserInput(name)
  }

  def copyGeogCSFrom(rhs: OSRSpatialReference): Int = AnyRef.synchronized {
    underlying.CopyGeogCSFrom(rhs.underlying)
  }

  def setTOWGS84(p1: Double, p2: Double, p3: Double, p4: Double, p5: Double, p6: Double, p7: Double): Int = AnyRef.synchronized {
    underlying.SetTOWGS84(p1, p2, p3, p4, p5, p6, p7)
  }

  def getTOWGS84(argout: Array[Double]): Int = AnyRef.synchronized {
    underlying.GetTOWGS84(argout)
  }

  def setLocalCS(pszName: String): Int = AnyRef.synchronized {
    underlying.SetLocalCS(pszName)
  }

  def setGeogCS(pszGeogName: String, pszDatumName: String, pszEllipsoidName: String, dfSemiMajor: Double, dfInvFlattening: Double, pszPMName: String, dfPMOffset: Double, pszUnits: String, dfConvertToRadians: Double): Int = AnyRef.synchronized {
    underlying.SetGeogCS(pszGeogName, pszDatumName, pszEllipsoidName, dfSemiMajor, dfInvFlattening, pszPMName, dfPMOffset, pszUnits, dfConvertToRadians)
  }

  def setGeogCS(pszGeogName: String, pszDatumName: String, pszEllipsoidName: String, dfSemiMajor: Double, dfInvFlattening: Double, pszPMName: String, dfPMOffset: Double, pszUnits: String): Int = AnyRef.synchronized {
    underlying.SetGeogCS(pszGeogName, pszDatumName, pszEllipsoidName, dfSemiMajor, dfInvFlattening, pszPMName, dfPMOffset, pszUnits)
  }

  def setGeogCS(pszGeogName: String, pszDatumName: String, pszEllipsoidName: String, dfSemiMajor: Double, dfInvFlattening: Double, pszPMName: String, dfPMOffset: Double): Int = AnyRef.synchronized {
    underlying.SetGeogCS(pszGeogName, pszDatumName, pszEllipsoidName, dfSemiMajor, dfInvFlattening, pszPMName, dfPMOffset)
  }

  def setGeogCS(pszGeogName: String, pszDatumName: String, pszEllipsoidName: String, dfSemiMajor: Double, dfInvFlattening: Double, pszPMName: String): Int = AnyRef.synchronized {
    underlying.SetGeogCS(pszGeogName, pszDatumName, pszEllipsoidName, dfSemiMajor, dfInvFlattening, pszPMName)
  }

  def setGeogCS(pszGeogName: String, pszDatumName: String, pszEllipsoidName: String, dfSemiMajor: Double, dfInvFlattening: Double): Int = AnyRef.synchronized {
    underlying.SetGeogCS(pszGeogName, pszDatumName, pszEllipsoidName, dfSemiMajor, dfInvFlattening)
  }

  def setProjCS(name: String): Int = AnyRef.synchronized {
    underlying.SetProjCS(name)
  }

  def setProjCS: Int = AnyRef.synchronized {
    underlying.SetProjCS
  }

  def setGeocCS(name: String): Int = AnyRef.synchronized {
    underlying.SetGeocCS(name)
  }

  def setGeocCS: Int = AnyRef.synchronized {
    underlying.SetGeocCS
  }

  def setVertCS(VertCSName: String, VertDatumName: String, VertDatumType: Int): Int = AnyRef.synchronized {
    underlying.SetVertCS(VertCSName, VertDatumName, VertDatumType)
  }

  def setVertCS(VertCSName: String, VertDatumName: String): Int = AnyRef.synchronized {
    underlying.SetVertCS(VertCSName, VertDatumName)
  }

  def setVertCS(VertCSName: String): Int = AnyRef.synchronized {
    underlying.SetVertCS(VertCSName)
  }

  def setVertCS: Int = AnyRef.synchronized {
    underlying.SetVertCS
  }

  def setCompoundCS(name: String, horizcs: OSRSpatialReference, vertcs: OSRSpatialReference): Int = AnyRef.synchronized {
    underlying.SetCompoundCS(name, horizcs.underlying, vertcs.underlying)
  }

  def importFromWkt(ppszInput: String): Int = AnyRef.synchronized {
    underlying.ImportFromWkt(ppszInput)
  }

  def importFromProj4(ppszInput: String): Int = AnyRef.synchronized {
    underlying.ImportFromProj4(ppszInput)
  }

  def importFromUrl(url: String): Int = AnyRef.synchronized {
    underlying.ImportFromUrl(url)
  }

  def importFromESRI(ppszInput: Vector[_]): Int = AnyRef.synchronized {
    val vector = new java.util.Vector[Any]()
    vector.addAll(ppszInput.asJavaCollection)
    underlying.ImportFromESRI(vector)
  }

  def importFromEPSG(arg: Int): Int = AnyRef.synchronized {
    underlying.ImportFromEPSG(arg)
  }

  def importFromEPSGA(arg: Int): Int = AnyRef.synchronized {
    underlying.ImportFromEPSGA(arg)
  }

  def importFromPCI(proj: String, units: String, argin: Array[Double]): Int = AnyRef.synchronized {
    underlying.ImportFromPCI(proj, units, argin)
  }

  def importFromPCI(proj: String, units: String): Int = AnyRef.synchronized {
    underlying.ImportFromPCI(proj, units)
  }

  def importFromPCI(proj: String): Int = AnyRef.synchronized {
    underlying.ImportFromPCI(proj)
  }

  def importFromUSGS(proj_code: Int, zone: Int, argin: Array[Double], datum_code: Int): Int = AnyRef.synchronized {
    underlying.ImportFromUSGS(proj_code, zone, argin, datum_code)
  }

  def importFromUSGS(proj_code: Int, zone: Int, argin: Array[Double]): Int = AnyRef.synchronized {
    underlying.ImportFromUSGS(proj_code, zone, argin)
  }

  def importFromUSGS(proj_code: Int, zone: Int): Int = AnyRef.synchronized {
    underlying.ImportFromUSGS(proj_code, zone)
  }

  def importFromUSGS(proj_code: Int): Int = AnyRef.synchronized {
    underlying.ImportFromUSGS(proj_code)
  }

  def importFromXML(xmlString: String): Int = AnyRef.synchronized {
    underlying.ImportFromXML(xmlString)
  }

  def importFromERM(proj: String, datum: String, units: String): Int = AnyRef.synchronized {
    underlying.ImportFromERM(proj, datum, units)
  }

  def importFromMICoordSys(pszCoordSys: String): Int = AnyRef.synchronized {
    underlying.ImportFromMICoordSys(pszCoordSys)
  }

  def importFromOzi(papszLines: Vector[_]): Int = AnyRef.synchronized {
    val vector = new java.util.Vector[Any]()
    vector.addAll(papszLines.asJavaCollection)
    underlying.ImportFromOzi(vector)
  }

  def exportToWkt(argout: Array[String]): Int = AnyRef.synchronized {
    underlying.ExportToWkt(argout)
  }

  def exportToPrettyWkt(argout: Array[String], simplify: Int): Int = AnyRef.synchronized {
    underlying.ExportToPrettyWkt(argout, simplify)
  }

  def exportToPrettyWkt(argout: Array[String]): Int = AnyRef.synchronized {
    underlying.ExportToPrettyWkt(argout)
  }

  def exportToProj4(argout: Array[String]): Int = AnyRef.synchronized {
    underlying.ExportToProj4(argout)
  }

  def exportToPCI(proj: Array[String], units: Array[String], parms: Array[Double]): Int = AnyRef.synchronized {
    underlying.ExportToPCI(proj, units, parms)
  }

  def exportToUSGS(code: Array[Int], zone: Array[Int], parms: Array[Double], datum: Array[Int]): Int = AnyRef.synchronized {
    underlying.ExportToUSGS(code, zone, parms, datum)
  }

  def exportToXML(argout: Array[String], dialect: String): Int = AnyRef.synchronized {
    underlying.ExportToXML(argout, dialect)
  }

  def exportToXML(argout: Array[String]): Int = AnyRef.synchronized {
    underlying.ExportToXML(argout)
  }

  def exportToMICoordSys(argout: Array[String]): Int = AnyRef.synchronized {
    underlying.ExportToMICoordSys(argout)
  }

  def cloneGeogCS: OSRSpatialReference = AnyRef.synchronized {
    OSRSpatialReference(underlying.CloneGeogCS)
  }
  
  def validate: Int = AnyRef.synchronized {
    underlying.Validate
  }

  def stripCTParms: Int = AnyRef.synchronized {
    underlying.StripCTParms
  }

  def fixupOrdering: Int = AnyRef.synchronized {
    underlying.FixupOrdering
  }

  def fixup: Int = AnyRef.synchronized {
    underlying.Fixup
  }

  def morphToESRI: Int = AnyRef.synchronized {
    underlying.MorphToESRI
  }

  def morphFromESRI: Int = AnyRef.synchronized {
    underlying.MorphFromESRI
  }

  def convertToOtherProjection(other_projection: String, options: Vector[_]): OSRSpatialReference = AnyRef.synchronized {
    val vector = new java.util.Vector[Any]()
    vector.addAll(options.asJavaCollection)
    OSRSpatialReference(underlying.ConvertToOtherProjection(other_projection, vector))
  }

  def convertToOtherProjection(other_projection: String): OSRSpatialReference = AnyRef.synchronized {
    OSRSpatialReference(underlying.ConvertToOtherProjection(other_projection))
  }

  /** GeoTrellis helper methods */

  def toCRS: CRS = CRS.fromString(exportToProj4)
}

object OSRSpatialReference {
  def apply(wkt: String): OSRSpatialReference = OSRSpatialReference(new SpatialReference(wkt))
  def apply(): OSRSpatialReference = OSRSpatialReference(new SpatialReference())
}
