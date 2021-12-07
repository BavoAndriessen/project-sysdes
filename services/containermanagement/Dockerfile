# traag en groter
FROM openjdk:11
COPY . .
RUN apt-get install -y curl && ./mvnw package -DskipTests
CMD ["java", "-jar", "target/containermanagement-0.0.1-SNAPSHOT.jar", "$SPRINGDATASOURCEURL"]

# sneller, maar moet maven hebben en apart builden
#FROM openjdk:11-jre-slim
#COPY target/containermanagement-0.0.1-SNAPSHOT.jar .
#CMD ["java", "-jar", "/containermanagement-0.0.1-SNAPSHOT.jar", "--spring.datasource.url=jdbc:postgresql://containermanagement.default.svc.cluster.local:5432/containers"]
