FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/application.jar"]
