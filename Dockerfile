#
# Build stage
#
FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/knowledgeManagementSystem-0.0.1.jar /usr/local/lib/demo.jar
EXPOSE 55555
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]