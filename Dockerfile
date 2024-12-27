# Etapa de build
FROM maven:3.8.5-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Etapa final
FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/bds06-0.0.1-SNAPSHOT.jar MovieFlixV2.jar
ENTRYPOINT ["java","-jar","/MovieFlixV2.jar"]