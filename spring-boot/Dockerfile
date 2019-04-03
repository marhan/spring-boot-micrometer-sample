FROM openjdk:11-jdk-slim
VOLUME /tmp
MAINTAINER Markus Hanses <hanses.markus@gmail.com>
ADD build/libs/*.jar app.jar

ENV JAVA_OPTS=""
ENV SPRING_PROFILE="docker,chaos-monkey"

ENTRYPOINT exec java $JAVA_OPTS \
 -Djava.security.egd=file:/dev/./urandom \
 -Dspring.profiles.active=$SPRING_PROFILE \
 -jar app.jar