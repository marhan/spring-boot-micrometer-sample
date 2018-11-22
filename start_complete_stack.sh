#!/usr/bin/env bash

docker-compose stop
cd spring-boot/ && ./gradlew build && cd ..
docker-compose up -d --build
