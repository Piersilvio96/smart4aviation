FROM gradle:7.2.0-jdk11 AS build

WORKDIR /app

# Copy the Gradle build files and settings
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY gradlew /app/

# Copy the source code
COPY src /app/src

# Build the Java application
RUN ./gradlew build

# Stage 2: Create a smaller JRE-only image
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=build /app/build/libs/*.jar /app/

# Define the command to run your Java application
CMD ["java", "-jar", "*.jar"]