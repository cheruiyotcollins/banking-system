FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/store-of-value-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]