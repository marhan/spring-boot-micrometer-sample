# Spring Boot Micrometer sample project

This project is meant as sandbox for experiments and sample implementation for Spring Boot 2 with Micrometer.

The state of this repository is still **work in progress**, but should run without issues.

# Requirements (Run)

* Docker / Docker Compose (RAM 4 GB min.)

# Requirements (Dev)

* Java JDK 11
* Node.js 8

# Introductions

* [Concepts of Micrometer](https://micrometer.io/docs/concepts)
* [Spring Boot Supported Metrics](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics-meter)

# Run complete monitoring environment

```bash
./start_complete_stack.sh
```

This script will do the steps via Docker ...

* remove all docker volumes of previous builds.
* build the Vue.js Frontend frontend project.
* build the Spring Boot backend project.
* start and configured all monitoring products like (Elasticsearch, Kibana, Prometheus, ...)

# Stop and destroy all containers with its volumes manually

```bash
docker-compose stop && docker-compose rm -f -v
docker volume remove spring-boot-micrometer-sample_elasticsearch_data
docker volume remove spring-boot-micrometer-sample_grafana_data
docker volume remove spring-boot-micrometer-sample_prometheus_data
docker volume remove spring-boot-micrometer-sample_kibana_data
```

# Further informations

* [Prometheus, Grafana Setup](https://github.com/vegasbrianc/prometheus)
* [Micrometer Samples](https://github.com/micrometer-metrics/micrometer/tree/master/samples/micrometer-samples-boot1/src/main/java/io/micrometer/boot1/samples)

# TODO

* Frontend implementation with vue.js
* Ability to create new apartments. (inmemory persistence)
* Timer Meter 
* Latency via Chaos Monkey
* Spring Integration tests
* Build and test with Travis-CI
* Spring Boot Dev Tools
* Send metrics to InfluxDB