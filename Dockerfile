FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/bds06-0.0.1-SNAPSHOT.jar MovieFlixV2.jar
ENTRYPOINT ["java","-jar","/MovieFlixV2.jar"]
