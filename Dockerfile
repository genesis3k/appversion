# 1. Run as standalone Docker to build the image without using Travis CI
# Please uncomment below lines to run as standalone Docker Build
# FROM maven:3.5-jdk-8-alpine as build
# WORKDIR /app
# ADD ./ /app
# RUN mvn clean install

# FROM openjdk:8-jre-alpine
# WORKDIR /app
# COPY --from=build /app/target/versiondemo-0.0.1-SNAPSHOT.jar /app
# EXPOSE 8080
# ENTRYPOINT ["sh", "-c", "java -jar /app/versiondemo-0.0.1-SNAPSHOT.jar"]

# 2. Use with Travis CI. Run at the end of CI pipeline. 
# As Build will be done by Travis CI, this just creates the container image
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY /target/versiondemo-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar /app/versiondemo-0.0.1-SNAPSHOT.jar"]