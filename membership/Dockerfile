FROM openjdk:11.0.10-jre-slim

EXPOSE 8083
COPY ./target/membership-*.jar /app/membership.jar

ENTRYPOINT ["java","-jar","/app/membership.jar"]