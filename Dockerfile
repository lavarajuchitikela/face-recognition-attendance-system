FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy backend code
COPY attendance-backend/pom.xml .
COPY attendance-backend/src ./src

# Build JAR
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 9090
CMD ["java","-jar","app.jar"]
