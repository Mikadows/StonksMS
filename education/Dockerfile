FROM openjdk:11.0.10-jre-slim

EXPOSE 8081
COPY ./target/education-*.jar /app/education.jar

ENTRYPOINT ["java","-jar","/app/education.jar"]