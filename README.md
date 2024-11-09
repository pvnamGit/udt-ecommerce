# UDT E-Commerce Project

## Introduction

This project is an e-commerce platform built with Spring Boot. It includes a variety of features such as user management, product management, order handling, billing, and integration with third-party APIs. The project also demonstrates how to manage data synchronization, JWT-based authentication, and database interaction.

## Running Tests for Algorithm in `ContainerRentalSolutionTest.java`

To ensure that the algorithm in the `src/test/java/com/udt/algorithm/ContainerRentalSolutionTest.java` file works as expected, follow these steps:

### **Prerequisites**
- **Java Development Kit (JDK) version 17** or higher installed.
- **Gradle** installed (the build system for the project).

### **Steps to Run Tests:**

1. **Navigate to the Project Directory:**
   Open your terminal and navigate to the root directory of the project where the `build.gradle` file is located.

2. **Run the Tests Using Gradle:**
   Use the following command to run the tests:
   
   ```bash
   ./gradlew test --tests "com.udt.algorithm.ContainerRentalSolutionTest"
This command will:

- Compile the tests.
- Execute the tests in the ContainerRentalSolutionTest class.
- Display the results in the terminal.
3. **View Test Results**: After running the tests, Gradle will show the results in the terminal. If the tests pass, you’ll see a message like:

 ```bash
BUILD SUCCESSFUL in 5s
```
If any tests fail, the terminal will show detailed error messages so you can debug the issue.

4. **Check Test Reports (Optional)**: You can also find the detailed test reports in the build/reports/tests/test/index.html file. Open this file in your browser to view the detailed test results in a more readable format.

5. ## Running the Project via Docker
6. ### 1. Build the Docker Image:
7. Before running the application with Docker, you need to build the Docker image for the Spring Boot application. Make sure that your application is built using Gradle first:
8. ```bash
   ./gradlew build
   ```
   Once the build completes successfully, you can proceed with creating the Docker image.

### 2. Set Up Docker and Docker Compose:
**Dockerfile**
  The Dockerfile is configured to run the Spring Boot application within a Docker container. It will:

  Use the `openjdk:17-jdk-slim` base image.
  Copy the built JAR file (`udt_ecommerce-0.0.1-SNAPSHOT.jar`) into the container.
  Set the working directory and expose port `8080`.
  **docker-compose.yml**
  The `docker-compose.yml` file defines two services:
  
  Spring Boot Application (`springboot-app`): Builds the application from the Dockerfile, maps environment variables, and exposes port 8080.
  PostgreSQL Database (`db`): Runs the PostgreSQL container and ensures the database is ready before starting the application.

### 3. Running the Application via Docker Compose:
To start the application and the database using Docker Compose, follow these steps:

Ensure Docker and Docker Compose are Installed:

**Start the Application and Database with Docker Compose**: Run the following command to build and start the application and the database container:
```bash
docker-compose up --build
```
