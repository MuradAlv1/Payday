FROM openjdk:17-jdk-slim

COPY order-executor/build/libs/order-executor-0.0.1-SNAPSHOT.jar order-executor.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","/order-executor.jar"]