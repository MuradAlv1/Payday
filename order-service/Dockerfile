FROM openjdk:17-jdk-slim

COPY order-service/build/libs/order-service-0.0.1-SNAPSHOT.jar order-service.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/order-service.jar"]