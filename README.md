# Spring Boot Micrometer sample project

**Work in progress**


# Micrometer

* [Concepts of Micrometer](https://micrometer.io/docs/concepts)
* [Spring Boot Supported Metrics](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics-meter)


# Spring Boot application

Requirements: JDK 10 (Gradle has an issue with JDK 11)

Find project in directory 'spring-boot'.

# Start monitoring

## Build spring boot application

```bash
./gradlew build
```

# Start docker stack

```bash
docker-compose up
```

# Development

## Run locally

```bash
./gradlew clean bootRun -Denvironment=local
```

## Run in docker with dependency

```bash
docker-compose stop && cd spring-boot/ && ./gradlew build && cd .. && docker-compose up -d --build
```

## Rebuild complete stack

```bash
docker-compose stop && docker-compose rm -f -v && cd spring-boot && ./gradlew clean build && cd .. && docker-compose up -d --force-recreate --build
```

# Find metrics

* [Actuators of your application](http://localhost:8080/actuator)

# Stop docker instances and destroy 

```bash
docker-compose stop && docker-compose rm -f -v
```


# Stop and remove all Containers

```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

# Remove docker volumes

```bash
docker volume remove spring-boot-micrometer-sample_elasticsearch_data
docker volume remove spring-boot-micrometer-sample_grafana_data
docker volume remove spring-boot-micrometer-sample_prometheus_data
docker volume remove spring-boot-micrometer-sample_kibana_data

```


# Credits

* [Prometheus, Grafana Setup](https://github.com/vegasbrianc/prometheus)
* [Micrometer Samples](https://github.com/micrometer-metrics/micrometer/tree/master/samples/micrometer-samples-boot1/src/main/java/io/micrometer/boot1/samples)