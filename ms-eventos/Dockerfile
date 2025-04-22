FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/ms-eventos-0.0.1-SNAPSHOT.jar /app/ms-eventos.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "ms-eventos.jar"]
