FROM openjdk:17-jdk-slim

COPY account-service/build/libs/account-service-0.0.1-SNAPSHOT.jar account-service.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","/account-service.jar"]