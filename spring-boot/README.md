# Spring Boot application

## Requirements 

* JDK 11

## Dev

run locally

```bash
./gradlew bootRun --args='--spring.profiles.active=local,chaos-monkey'
```
The monitoring systems are not connected, if the app runs locally.   

# Service

* [Swagger UI](http://localhost:8082/swagger-ui.html)
* [Actuator](http://localhost:8082/actuator)
* [H2 Console](http://localhost:8082/h2-console)

# Further reading

* [SpringFox](http://springfox.github.io/springfox/)
