FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /src

# Copier pom.xml en premier pour maximiser le cache Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier les sources et packager l'application
COPY src ./src
RUN mvn package -DskipTests -B

FROM eclipse-temurin:17-jre
WORKDIR /app

# Adapter le motif si l'artifactId/version changent dans pom.xml
COPY --from=build /src/target/tp-junit-boutique-*.jar app.jar

EXPOSE 8080

CMD ["java", "-cp", "app.jar", "fr.boutique.BoutiqueApplication"]