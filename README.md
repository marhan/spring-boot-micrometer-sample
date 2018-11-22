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

## Run in development mode.

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

## Rebuild container while docker container are running

```bash
docker-compose up -d --no-deps --build springboot
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