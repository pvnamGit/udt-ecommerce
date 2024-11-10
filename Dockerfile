# Stage 1: Build the application
FROM openjdk:17-jdk-slim as builder

WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew . 
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Grant execute permissions for the Gradle wrapper
RUN chmod +x gradlew

# Run a Gradle command to download dependencies only
RUN ./gradlew build -x test --no-daemon || return 0

# Copy the rest of the application files
COPY src src

# Run the full Gradle build
RUN ./gradlew clean build -x test --no-daemon

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=builder /app/build/libs/udt-ecommerce-0.0.1-SNAPSHOT.jar udt_ecommerce.jar

# Expose the application port
EXPOSE 8080

# Set the environment variable for Spring profile (optional, if needed)
ENV SPRING_PROFILES_ACTIVE=dev

# Run the application
CMD ["java", "-jar", "udt_ecommerce.jar"]