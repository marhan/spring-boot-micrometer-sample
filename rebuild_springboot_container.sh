#!/usr/bin/env bash


docker-compose stop springboot
cd spring-boot/ && ./gradlew build && cd ..
docker-compose up -d --no-deps --build --force-recreate springboot