# Spring Boot Micrometer sample project

**Work in progress**


# Micrometer

* [Concepts of Micrometer](https://micrometer.io/docs/concepts)
* [Spring Boot Supported Metrics](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics-meter)


# Spring Boot application

Go into subdirectory 'spring-boot'.


# Start monitoring

## Build spring boot application

```bash
./gradlew clean build
```

# Start docker stack

```bash
docker-compose up

# rebuild springboot only
docker-compose up -d --no-deps --build springboot
```

# Development

## Run locally

```bash
./gradlew bootRun
```

## Run in docker with dependency

```bash
docker-compose stop
cd spring-boot/ && ./gradlew build && cd ..
docker-compose up -d --build
```

## Rebuild complete stack

```bash
docker-compose stop && docker-compose rm -f -v && cd spring-boot && ./gradlew clean build && cd .. && docker-compose up -d --force-recreate --build
```

# Find metrics

* [Actuators of your application](http://localhost:8080/actuator)


# Stop and remove all Containers

```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```


# Credits

* [Prometheus, Grafana Setup](https://github.com/vegasbrianc/prometheus)
* [Micrometer Samples](https://github.com/micrometer-metrics/micrometer/tree/master/samples/micrometer-samples-boot1/src/main/java/io/micrometer/boot1/samples)