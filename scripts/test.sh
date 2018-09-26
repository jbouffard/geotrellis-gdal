docker run \
  -it \
  -v $(pwd)/build.sbt:/root/build.sbt \
  -v $(pwd)/project:/root/project \
  -v $(pwd)/src:/root/src \
  -v ~/.ivy2:/root/.ivy2 \
  geotrellis/gdal-test \
  cd ~ & sbt test
