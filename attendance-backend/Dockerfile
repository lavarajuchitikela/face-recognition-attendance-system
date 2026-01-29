FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Install OpenCV runtime libs
RUN apt-get update && apt-get install -y libopencv-dev

EXPOSE 9090
CMD ["java","-jar","app.jar"]
