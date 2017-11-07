package geotrellis.gdal

import geotrellis.proj4.LatLng
import geotrellis.spark._
import geotrellis.spark.ingest._
import geotrellis.spark.tiling._
import geotrellis.spark.testkit.TestEnvironment

import org.apache.hadoop.fs.Path

import java.time.ZonedDateTime

import org.scalatest._

class IngestSpec extends FunSpec
    with Matchers
    with OnlyIfGdalInstalled
    with TestEnvironment
{

  describe("Ingest") {
    ifGdalInstalled {
      val expectedKeys = List(
        SpaceTimeKey(SpatialKey(1,1),TemporalKey(ZonedDateTime.parse("2006-03-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(2,0),TemporalKey(ZonedDateTime.parse("2006-01-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(2,1),TemporalKey(ZonedDateTime.parse("2006-02-15T00:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(0,0),TemporalKey(ZonedDateTime.parse("2006-01-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(2,1),TemporalKey(ZonedDateTime.parse("2006-01-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(2,1),TemporalKey(ZonedDateTime.parse("2006-03-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(0,1),TemporalKey(ZonedDateTime.parse("2006-03-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(0,1),TemporalKey(ZonedDateTime.parse("2006-02-15T00:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(0,1),TemporalKey(ZonedDateTime.parse("2006-01-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(1,0),TemporalKey(ZonedDateTime.parse("2006-02-15T00:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(1,0),TemporalKey(ZonedDateTime.parse("2006-01-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(0,0),TemporalKey(ZonedDateTime.parse("2006-02-15T00:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(1,0),TemporalKey(ZonedDateTime.parse("2006-03-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(1,1),TemporalKey(ZonedDateTime.parse("2006-01-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(1,1),TemporalKey(ZonedDateTime.parse("2006-02-15T00:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(2,0),TemporalKey(ZonedDateTime.parse("2006-02-15T00:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(2,0),TemporalKey(ZonedDateTime.parse("2006-03-16T12:00:00.000Z"))),
        SpaceTimeKey(SpatialKey(0,0),TemporalKey(ZonedDateTime.parse("2006-03-16T12:00:00.000Z")))
      )

      it("should ingest time-band NetCDF") {
        val source = sc.netCdfRDD(new Path(inputHome, "ipcc-access1-tasmin.nc"))
        Ingest[TemporalProjectedExtent, SpaceTimeKey](source, LatLng, FloatingLayoutScheme(256)){ (rdd, level) =>
          val ingestKeys = rdd.keys.collect()
          info(ingestKeys.toList.toString)
          // Ingest uses a tileSize of 512x512, so we have less tiles
          ingestKeys should contain theSameElementsAs
            expectedKeys.filterNot(stk => stk.spatialKey.col == 2 || stk.spatialKey.row == 1)
        }
      }

      it("should ingest time-band NetCDF in stages") {
        val source = sc.netCdfRDD(new Path(inputHome, "ipcc-access1-tasmin.nc"))
        val (zoom, rmd) = source.collectMetadata[SpaceTimeKey](LatLng, FloatingLayoutScheme(256))
        val tiled = source.cutTiles[SpaceTimeKey](rmd)
        val ingestKeys = tiled.keys.collect()
        ingestKeys should contain theSameElementsAs expectedKeys
      }
    }
  }
}
