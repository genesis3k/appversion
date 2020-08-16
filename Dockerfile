# FROM maven:3.5-jdk-8-alpine as build
# WORKDIR /app
# ADD ./ /app
# RUN mvn clean install

# FROM openjdk:8-jre-alpine
# WORKDIR /app
# COPY --from=build /app/target/versiondemo-0.0.1-SNAPSHOT.jar /app
# EXPOSE 8080
# ENTRYPOINT ["sh", "-c", "java -jar /app/versiondemo-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY /target/versiondemo-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar /app/versiondemo-0.0.1-SNAPSHOT.jar"]