FROM openjdk:11-jdk-slim
# work workdirectory
WORKDIR /app
# copy sources
COPY . .
# build application
RUN ./gradlew build -x test --no-daemon
EXPOSE 8080
CMD ["java", "-jar", "build/libs/spring-boot-micrometer-1.jar"]