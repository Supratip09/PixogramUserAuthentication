FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/PixogramUserAuthentication-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} pixogram-user-authentication.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/pixogram-user-authentication.jar"]