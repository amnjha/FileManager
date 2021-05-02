FROM maven:3.8-jdk-11 as BUILD

COPY src /app/src
COPY pom.xml  /app

RUN mvn clean install -DskipTests -f /app/pom.xml

FROM openjdk:11.0.11-slim
COPY --from=BUILD /app/target/filemanager-*.jar /app/filemanager.jar

RUN mkdir -p /app/file-storage
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/filemanager.jar"]

