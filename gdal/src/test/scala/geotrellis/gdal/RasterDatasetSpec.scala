package geotrellis.gdal

import geotrellis.proj4._
import geotrellis.raster._
import geotrellis.raster.testkit._
import geotrellis.raster.io.geotiff._

import org.scalatest._


class RasterDatasetSpec extends FunSpec
  with RasterMatchers
  with OnlyIfGdalInstalled
{

  val path = "src/test/resources/data/slope.tif"

  describe("reading a GeoTiff") {
    ifGdalInstalled {
      it("should match one read with GeoTools") {
        println("Reading with GDAL...")
        val source = RasterDataset(path)
        val gdRaster = Raster(source.read(source.gridBounds).get.band(0), source.extent)
        val gdExt = source.extent
        println("Reading with GeoTools....")
        val Raster(gtRaster, gtExt) = SinglebandGeoTiff(path).raster
        println("Done.")

        gdExt.xmin should be(gtExt.xmin +- 0.00001)
        gdExt.xmax should be(gtExt.xmax +- 0.00001)
        gdExt.ymin should be(gtExt.ymin +- 0.00001)
        gdExt.ymax should be(gtExt.ymax +- 0.00001)

        gdRaster.cols should be(gtRaster.cols)
        gdRaster.rows should be(gtRaster.rows)

        gdRaster.cellType.toString.take(7) should be(gtRaster.cellType.toString.take(7))

        println("Comparing rasters...")
        for (col <- 0 until gdRaster.cols) {
          for (row <- 0 until gdRaster.rows) {
            val actual = gdRaster.getDouble(col, row)
            val expected = gtRaster.getDouble(col, row)
            withClue(s"At ($col, $row): GDAL - $actual  GeoTools - $expected") {
              isNoData(actual) should be(isNoData(expected))
              if (isData(actual)) actual should be(expected)
            }
          }
        }
      }

      it("should read CRS from file") {
        val rasterDataSet = RasterDataset("src/test/resources/data/geotiff-test-files/all-ones.tif")
        rasterDataSet.crs.toProj4String should equal(LatLng.toProj4String)
      }
    }
  }

  describe("reading a JPEG2000") {
    ifGdalWithJpeg2000Installed {
      val lengthExpected = 100
      val jpeg2000Path = "src/test/resources/data/jpeg2000-test-files/testJpeg2000.jp2"

      it("should read a JPEG2000 from a file") {

        val source = RasterDataset(jpeg2000Path)
        val extent: RasterExtent = source.rasterExtent

        extent.cols should be (lengthExpected)
        extent.rows should be (lengthExpected)
      }
    }
  }
}
