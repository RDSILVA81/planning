FROM openjdk:17
MAINTAINER Rafael
COPY target/planning-0.0.1.jar planning-0.0.1.jar
ENTRYPOINT ["java","-jar","/planning-0.0.1.jar"]