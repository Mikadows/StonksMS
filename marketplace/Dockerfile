FROM openjdk:11.0.10-jre-slim

EXPOSE 8082
COPY ./target/marketplace-*.jar /app/marketplace.jar

ENTRYPOINT ["java","-jar","/app/marketplace.jar"]