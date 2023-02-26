FROM openjdk:17.0.1-jdk-slim

COPY ../../target/*.jar  /app/app.jar
WORKDIR /app
RUN mkdir -p /app/uploads
EXPOSE 8000
ENTRYPOINT ["java","-jar","app.jar"]