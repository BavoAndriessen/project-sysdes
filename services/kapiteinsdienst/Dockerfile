FROM openjdk:11
COPY . .
RUN apt-get install -y curl && ./mvnw package -DskipTests
CMD ["java", "-jar", "./target/kapiteinsdienst-0.0.1-SNAPSHOT.jar"]
