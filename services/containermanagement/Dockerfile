# traag en groter
#FROM openjdk:11
#COPY . .
#RUN apt-get install -y curl && ./mvnw package -DskipTests
#ENTRYPOINT ["java", "-jar", "target/containermanagement-0.0.1-SNAPSHOT.jar", "$DOCKERCOMPOSEDATASOURCEURL"]


# sneller, maar je moet maven hebben en apart builden voor je container maakt (jar file moet al bestaan)
#FROM openjdk:11-jre-slim
#COPY target/containermanagement-0.0.1-SNAPSHOT.jar .
#ENTRYPOINT ["java", "-jar", "/containermanagement-0.0.1-SNAPSHOT.jar", $DOCKERCOMPOSEDATASOURCEURL"]


# traag maar wel klein
FROM openjdk:11 AS MAVEN_BUILD
COPY . .
RUN apt-get install -y curl && ./mvnw package -DskipTests
FROM openjdk:11-jre-slim
COPY --from=MAVEN_BUILD  target/containermanagement-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/containermanagement-0.0.1-SNAPSHOT.jar", "$DOCKERCOMPOSEDATASOURCEURL"]
