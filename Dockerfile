FROM openjdk:11.0.10-jre-slim

EXPOSE 8080
COPY ./target/StonksMS-*.jar /app/StonksMS.jar

ENTRYPOINT ["java", "-jar", "/app/StonksMS.jar"]