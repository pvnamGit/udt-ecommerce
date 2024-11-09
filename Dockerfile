# Use a Java base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the compiled JAR file from Gradle's build/libs directory into the container
COPY build/libs/udt-ecommerce-0.0.1-SNAPSHOT.jar udt_ecommerce.jar

# Expose the application port
EXPOSE 8080

# Set the environment variable for Spring profile (optional, if needed)
ENV SPRING_PROFILES_ACTIVE=dev

# Run the application
CMD ["java", "-jar", "udt_ecommerce.jar"]
