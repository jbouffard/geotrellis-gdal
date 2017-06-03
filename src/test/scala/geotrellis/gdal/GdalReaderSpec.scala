package geotrellis.gdal

import geotrellis.gdal.io.hadoop.GdalInputFormat.{GdalFileInfo, GdalRasterInfo}
import geotrellis.gdal.io.hadoop._
import geotrellis.proj4._
import geotrellis.raster._
import geotrellis.raster.io.geotiff._
import org.apache.spark.rdd.RDD
import org.scalatest.{Matchers, _}

class GdalReaderSpec extends FunSpec
  with Matchers
  with OnlyIfGdalInstalled
  with TestEnvironment
{

  val path = "src/test/resources/data/slope.tif"

  describe("reading a GeoTiff") {
    ifGdalInstalled {
      it("should match one read with GeoTools") {
        println("Reading with GDAL...")
        val (gdRaster, RasterExtent(gdExt, _, _, _, _)) = GdalReader.read(path)
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
        val rasterDataSet = Gdal.open("src/test/resources/data/geotiff-test-files/all-ones.tif")
        rasterDataSet.crs should equal(Some(LatLng))
      }
    }
  }

  describe("reading a JPEG2000") {
    ifGdalWithJpeg2000Installed {
      val lengthExpected = 100
      type TypeExpected = IntCells
      val jpeg2000Path = "src/test/resources/data/jpeg2000-test-files/testJpeg2000.jp2"

      it("should read a JPEG2000 from a file") {

        val (tile: Tile, extent: RasterExtent) = GdalReader.read(jpeg2000Path)

        extent.cols should be (lengthExpected)
        extent.rows should be (lengthExpected)
        tile.cellType shouldBe a [TypeExpected]
      }

      it("should load a JPEG2000 into an RDD") {
        val tileRdd: RDD[(GdalRasterInfo, Tile)] =
          sc.gdalRDD(new org.apache.hadoop.fs.Path(jpeg2000Path))

        val first = tileRdd.first()
        val fileInfo: GdalFileInfo = first._1.file
        val tile: Tile = first._2

        fileInfo.rasterExtent.cols should be (lengthExpected)
        fileInfo.rasterExtent.rows should be (lengthExpected)
        tile.cellType shouldBe a [TypeExpected]
      }
    }
  }
}
