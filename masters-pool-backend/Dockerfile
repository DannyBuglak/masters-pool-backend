FROM openjdk:22-jdk-slim
VOLUME /tmp
COPY build/libs/masters-pool-backend.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080