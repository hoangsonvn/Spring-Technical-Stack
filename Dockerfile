FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/springtechincalstack.jar

RUN mkdir /opt/springtechincalstack

COPY ${JAR_FILE} /opt/springtechincalstack/springtechincalstack.jar

ENTRYPOINT ["java","-jar","/opt/springtechincalstack/springtechincalstack.jar"]