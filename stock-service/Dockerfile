FROM openjdk:17-jdk-slim

COPY stock-service/build/libs/stock-service-0.0.1-SNAPSHOT.jar stock-service.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/stock-service.jar"]