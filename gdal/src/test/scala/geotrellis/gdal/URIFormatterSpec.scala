package geotrellis.gdal

import java.net.URI

import org.scalatest._


class URIFormatterSpec extends FunSpec with Matchers {
  describe("Formatting the given uris") {
    it("should format - https url") {
      val filePath = "www.radomdata.com/test-files/file-1.tiff"
      val url = s"https://$filePath"
      val expectedPath = s"/vsicurl/$url"

      URIFormatter(url) should be (expectedPath)
    }

    it("should format - file uri") {
      val filePath = "/home/jake/Documents/test-files/file-1.tiff"
      val uri = s"file://$filePath"
      val expectedPath = filePath

      URIFormatter(uri) should be (expectedPath)
    }

    it("should format - chained file uri") {
      val filePath = "/home/jake/Documents/test-files/files.zip"
      val uri = s"file://$filePath"
      val expectedPath = s"/vsizip/$filePath"

      URIFormatter(uri) should be (expectedPath)
    }

    it("should format - s3 uri") {
      val filePath = "test-files/nlcd/data/tiff-0.tiff"
      val uri = s"s3://$filePath"
      val expectedPath = s"/vsis3/$filePath"

      URIFormatter(uri) should be (expectedPath)
    }

    it("should format - chained s3 uri") {
      val filePath = "test-files/nlcd/data/data.gzip"
      val uri = s"s3://$filePath"
      val expectedPath = s"/vsigzip//vsis3/$filePath"

      URIFormatter(uri) should be (expectedPath)
    }

    it("should format - hdfs uri") {
      val filePath = "test-files/nlcd/data/tiff-0.tiff"
      val uri = s"hdfs://$filePath"
      val expectedPath = s"/vsihdfs/$uri"

      URIFormatter(uri) should be (expectedPath)
    }

    it("should format - Google Cloud Storage uri") {
      val filePath = "test-files/nlcd/data/tiff-0.tiff"
      val uri = s"gs://$filePath"
      val expectedPath = s"/vsigs/$filePath"

      URIFormatter(uri) should be (expectedPath)
    }

    it("should format - Azure uri") {
      val uri = "wasb://test-files@myaccount.blah.core.net/nlcd/data/tiff-0.tiff"
      val expectedPath = "/vsiaz/test-files/nlcd/data/tiff-0.tiff"

      URIFormatter(uri) should be (expectedPath)
    }
  }
}
