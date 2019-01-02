# Spring Boot Micrometer sample project

This project is meant as sandbox for experiments and sample implementation for Spring Boot 2 with Micrometer.

The state of this repository is still **work in progress**, but should run without issues.

# Requirements

* Java JDK 11
* Docker (RAM 4 GB min.)

# Introductions

* [Concepts of Micrometer](https://micrometer.io/docs/concepts)
* [Spring Boot Supported Metrics](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics-meter)

# Start Monitoring

```bash
./start_complete_stack.sh
```

This script will build the Spring Boot project via **Gradle** and build the whole docker environment via **Docker Compose**.

# Stop and destroy all containers with its volumes manually

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
* Frontend implementation with vue.js
* Build Spring Boot application within docker container
* Timer Meter 
* Latency via Chaos Monkey
* Spring Integration tests
* Build and test with Travis-CI
* Spring Boot Dev Tools
* Send metrics to InfluxDB