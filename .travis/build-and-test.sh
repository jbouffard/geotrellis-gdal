#!/bin/bash

./sbt -J-Xmx2G "++$TRAVIS_SCALA_VERSION" \
  "project gdal" test \
  "project gdal-etl" test || { exit 1; }
