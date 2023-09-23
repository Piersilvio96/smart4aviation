FROM gradle:7.2.0-jdk11 AS build
USER root
WORKDIR /app

# Copy the Gradle build files and settings
COPY . .

# Build the Java application
RUN ./gradlew build

# Stage 2: Create a smaller JRE-only image
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=build /app/build/libs/*.jar /app/

# Define the command to run your Java application
CMD ["java", "-jar", "smart4aviation-0.0.1-SNAPSHOT.jar"]