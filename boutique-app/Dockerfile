FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src

# Copier le pom en premier pour maximiser le cache Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests -B

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /src/target/boutique.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
