package geotrellis.gdal

import geotrellis.raster.testkit._

import cats.implicits._
import cats.effect.{ContextShift, IO}
import org.scalatest._

import java.io.File
import scala.concurrent.ExecutionContext

class GDALDatasetSpec extends FunSpec with RasterMatchers with OnlyIfGdalInstalled {
  val filePath = s"${new File("").getAbsolutePath()}/src/test/resources/img/aspect-tiled.tif"

  def filePathByIndex(i: Int): String = s"${new File("").getAbsolutePath()}/src/test/resources/img/aspect-tiled-$i.tif"

  describe("GDALDatasetSpec") {
    ifGdalInstalled {
      /** Simulate possible RF backsplash calls */
      def dirtyCallsDS(ds: GDALDataset): Unit = {
        ds.rasterExtent
        ds.crs
        ds.cellSize
        ds.extent
      }

      it("sgdal.open multithreaded test") {
        println(java.lang.Thread.activeCount())

        implicit val cs = IO.contextShift(ExecutionContext.global)

        val n = 1000
        (1 to n).toList.flatMap { _ =>
          (0 to 4).flatMap { i =>
            List(IO {
              dirtyCallsDS(sgdal.open(filePathByIndex(i)))
            }, IO {
              dirtyCallsDS(sgdal.open(filePathByIndex(i)))
            }, IO {
              dirtyCallsDS(sgdal.open(filePathByIndex(i)))
            })
          }
        }.parSequence.void.unsafeRunSync

        println(java.lang.Thread.activeCount())
      }

      it("GDAL.open multithreaded test") {
        println(java.lang.Thread.activeCount())

        implicit val cs = IO.contextShift(ExecutionContext.global)

        val n = 1000
        (1 to n).toList.flatMap { _ =>
          (0 to 4).flatMap { i =>
            List(IO {
              dirtyCallsDS(GDAL.open(filePathByIndex(i)))
            }, IO {
              dirtyCallsDS(GDAL.open(filePathByIndex(i)))
            }, IO {
              dirtyCallsDS(GDAL.open(filePathByIndex(i)))
            })
          }
        }.parSequence.void.unsafeRunSync

        println(java.lang.Thread.activeCount())
      }
    }
  }
}
