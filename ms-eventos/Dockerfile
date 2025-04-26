FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/ms-eventos-0.0.1-SNAPSHOT.jar /app/ms-eventos.jar

EXPOSE 8081

RUN apt-get update && apt-get install -y curl

ENTRYPOINT ["java", "-jar", "ms-eventos.jar"]
