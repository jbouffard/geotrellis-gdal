package geotrellis.gdal.osr

import org.gdal.osr.{CoordinateTransformation, SpatialReference, osr}

case class OSRCoordinateTransformation(underlying: CoordinateTransformation) {
  override protected def finalize(): Unit = {
    if(underlying != null) AnyRef.synchronized(underlying.delete)
    super.finalize()
  }

  def delete(): Unit = AnyRef.synchronized {
    if(underlying != null) underlying.delete
  }

  def transformPoint(x: Double, y: Double, z: Double): Array[Double] = AnyRef.synchronized {
    underlying.TransformPoint(x, y, z)
  }

  def transformPoint(x: Double, y: Double): Array[Double] = transformPoint(x, y, 0)

  def transformPoint(inout: Array[Double]): Unit = AnyRef.synchronized {
    underlying.TransformPoint(inout)
  }

  def transformPoint(argout: Array[Double], x: Double, y: Double, z: Double): Unit = AnyRef.synchronized {
    underlying.TransformPoint(argout, x, y, z)
  }

  def transformPoint(argout: Array[Double], x: Double, y: Double): Unit = AnyRef.synchronized {
    underlying.TransformPoint(argout, x, y)
  }

  def transformPoints(nCount: Array[Array[Double]]): Unit = AnyRef.synchronized {
    underlying.TransformPoints(nCount)
  }
}

object OSRCoordinateTransformation {
  def apply(src: SpatialReference, dst: SpatialReference): OSRCoordinateTransformation =
    AnyRef.synchronized(OSRCoordinateTransformation(osr.CreateCoordinateTransformation(src, dst)))

  def apply(src: OSRSpatialReference, dst: OSRSpatialReference): OSRCoordinateTransformation =
    apply(src.underlying, dst.underlying)

  def createCoordinateTransformation(src: SpatialReference, dst: SpatialReference): OSRCoordinateTransformation =
    apply(src, dst)

  def createCoordinateTransformation(src: OSRSpatialReference, dst: OSRSpatialReference): OSRCoordinateTransformation =
    apply(src, dst)
}
