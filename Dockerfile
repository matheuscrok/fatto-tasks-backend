FROM openjdk:11.0.14.1-jdk-buster
COPY target/*.jar task-1.0.0.jar
ENTRYPOINT ["java","-jar","/task-1.0.0.jar"]