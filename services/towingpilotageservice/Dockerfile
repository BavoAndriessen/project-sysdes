FROM openjdk:11 AS MAVEN_BUILD
COPY . .
RUN apt-get install -y curl && ./mvnw clean package -DskipTests
FROM openjdk:11-jre-slim
COPY --from=MAVEN_BUILD  target/towingpilotageservice-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/towingpilotageservice-0.0.1-SNAPSHOT.jar"]
