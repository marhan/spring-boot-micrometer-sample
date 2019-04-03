#!/usr/bin/env bash
echo
echo "Build Java project"
./gradlew build
echo
echo "Build Docker image"
docker build -f Dockerfile -t spring-boot-micrometer-sample .
docker tag spring-boot-micrometer-sample marhan/spring-boot-micrometer-sample:latest
docker push marhan/spring-boot-micrometer-sample
echo