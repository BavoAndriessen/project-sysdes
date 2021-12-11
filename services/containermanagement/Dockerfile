# traag en groter
#FROM openjdk:11
#COPY . .
#RUN apt-get install -y curl && ./mvnw package -DskipTests
#ENTRYPOINT ["java", "-jar", "target/containermanagement-0.0.1-SNAPSHOT.jar", "$URL"]
#CMD ["--spring.datasource.url=jdbc:postgresql://container-db.default.svc.cluster.local:5432/containers"]
# sneller, maar moet maven hebben en apart builden
FROM openjdk:11-jre-slim
COPY target/containermanagement-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/containermanagement-0.0.1-SNAPSHOT.jar", "$DOCKERCOMPOSEDATASOURCEURL"]
