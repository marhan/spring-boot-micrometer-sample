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

## Step 1

```bash
./start_complete_stack.sh
```

This script will do the steps via Docker ...

* start and configured all monitoring products like (Elasticsearch, Kibana, Prometheus, ...).
* build and start the Vue.js Frontend frontend project.
* build and start the Spring Boot backend project.

## Step 2

[Open application via browser](http://localhost:8091)

This script will do the steps via Docker ...

* stop and remove all docker containers.
* remove all created docker volumes.

# Dev hints

## Stop and destroy all containers with its volumes manually

```bash
docker-compose stop && docker-compose rm -f -v
docker volume remove spring-boot-micrometer-sample_elasticsearch_data
docker volume remove spring-boot-micrometer-sample_grafana_data
docker volume remove spring-boot-micrometer-sample_prometheus_data
docker volume remove spring-boot-micrometer-sample_kibana_data
```

## Rebuild one container

```bash
docker-compose up -d --no-deps --build grafana
```

## Start dev docker-compose

```bash
docker-compose -f docker-compose-dev.yml up -d --force-recreate --build
```


# Endpoints & Services

* [Vue.js Frontend](http://localhost:8091)
* [Spring Boot Backend](http://localhost:8090)
* [Spring Boot Actuator](http://localhost:8090/actuator)
* [Prometheus](http://localhost:9090/targets)
* [Grafana](http://localhost:3000)
* [Elastic Search](http://localhost:9200/)
* [Kibana](http://localhost:5601)

# Choas Monkey

* [Chaos Toolkit](https://docs.chaostoolkit.org/reference/usage/install/)
* [Chaos Toolkit Spring](https://docs.chaostoolkit.org/drivers/spring/)

## run experiment

```bash
chaos run chaos/experiment.json
```

# Further informations

* [Prometheus, Grafana Setup](https://github.com/vegasbrianc/prometheus)
* [Micrometer Samples](https://github.com/micrometer-metrics/micrometer/tree/master/samples/micrometer-samples-boot1/src/main/java/io/micrometer/boot1/samples)

# TODO

* Chaos Engineering via Chaos Monkey created by codecentric
* Trigger chaos via Chaos Toolkit
* Timer Meter 
* Ability to create new apartments
* Spring Integration tests
* Build and test with Travis-CI
* Spring Boot Dev Tools
* Send metrics to InfluxDB