FROM openjdk:17-jdk-slim

COPY notification-service/build/libs/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar

EXPOSE 8084

ENTRYPOINT ["java","-jar","/notification-service.jar"]