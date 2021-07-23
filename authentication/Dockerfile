FROM openjdk:11.0.10-jre-slim

EXPOSE 8084
COPY ./target/authentication-*.jar /app/authentication.jar

ENTRYPOINT ["java","-jar","/app/authentication.jar"]