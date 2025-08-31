# Étape 1 : Build Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Étape 2 : Image légère Java pour exécuter le .jar
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/gestionRh/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
