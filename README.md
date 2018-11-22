# Spring Boot Micrometer sample project

**Work in progress**


# Micrometer

* [Concepts of Micrometer](https://micrometer.io/docs/concepts)
* [Spring Boot Supported Metrics](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-metrics-meter)


# Spring Boot application

Go into subdirectory 'spring-boot'.

# Build

```bash
./gradlew test
```

# Start

# Development

## Run in development mode.

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

# Find metrics

* [Actuators of your application](http://localhost:8080/actuator)