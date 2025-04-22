FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/ms-participantes-0.0.1-SNAPSHOT.jar /app/ms-participantes.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "ms-participantes.jar"]
