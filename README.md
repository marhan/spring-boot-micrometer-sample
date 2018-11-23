# Spring Boot Micrometer sample project

This project is meant as sandbox for experiments and sample implementation for Spring Boot 2 with Micrometer.

# Introductions

* [Concepts of Micrometer](https://micrometer.io/docs/concepts)
* [Spring Boot Supported Metrics](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics-meter)

# Spring Boot application

Requirements: JDK 10 (Gradle has an issue with JDK 11)

# Start Monitoring

```bash
./start_complete_stack.sh
```

This script will build the Spring Boot project via **Gradle** and build the whole docker environment via **Docker Compose**.

# Development

## Run locally

```bash
cd spring-boot
./gradlew clean bootRun -Denvironment=local
```
The monitoring systems are not connected, if the app is running locally.   

# Find metrics

* [Application landing page](http://localhost:8090)

# Stop and destroy all containers with its volumes 

```bash
docker-compose stop && docker-compose rm -f -v
docker volume remove spring-boot-micrometer-sample_elasticsearch_data
docker volume remove spring-boot-micrometer-sample_grafana_data
docker volume remove spring-boot-micrometer-sample_prometheus_data
docker volume remove spring-boot-micrometer-sample_kibana_data
```

# Credits

* [Prometheus, Grafana Setup](https://github.com/vegasbrianc/prometheus)
* [Micrometer Samples](https://github.com/micrometer-metrics/micrometer/tree/master/samples/micrometer-samples-boot1/src/main/java/io/micrometer/boot1/samples)

# TODO
* Spring Integration tests
* Spring Boot Dev Tools
* Timer Meter 
* Send metrics to InfluxDB