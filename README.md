# UDT E-Commerce Project

## I. Introduction

This project is an e-commerce platform built with Spring Boot. It includes a variety of features such as user management, product management, order handling, billing, and integration with third-party APIs. The project also demonstrates how to manage data synchronization, JWT-based authentication, and database interaction.

## II. Running Tests for Algorithm
- Solution file: `src/main/java/com/udt/algorithm/ContainerRentalSolution.java`
- Test file: `ContainerRentalSolutionTest.java`
- To ensure that the algorithm in the `src/test/java/com/udt/algorithm/ContainerRentalSolutionTest.java` file works as expected, follow these steps:

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

## III. Running the Project via Docker
### 1. Docker and Docker Compose:
**Setup environment**

Create an `.env` file, then copy all key from `.env.docker` to `.env`

Example:
```text
CONTAINER_NAME=udt_ecommerce_db
PORT=5432:5432
SPRINGBOOT_CONTAINER_NAME=udt_ecommerce_app

URL_PREFIX=api/v1

DB_USERNAME=postgres
DB_PASSWORD=admin
DB_HOST=localhost
DB_PORT=5432
DB_NAME=udt_ecommerce

JWT_SECRET=9198c7305494e66746bf47dd5740f44198362f8bacdcabd664e23707698c55fc
JWT_EXPIRATION=604800000

DATA_INITIALIZE=true
```

**Dockerfile**

The Dockerfile is configured to run the Spring Boot application within a Docker container. It will:

  - Use the `openjdk:17-jdk-slim` base image.
  - Copy the built JAR file (`udt_ecommerce-0.0.1-SNAPSHOT.jar`) into the container.
  - Set the working directory and expose port `8080`.
  
**docker-compose.yml**
  
The `docker-compose.yml` file defines two services:
  
- **Spring Boot Application (`springboot-app`)**: Builds the application from the Dockerfile, maps environment variables, and exposes port 8080.
- **PostgreSQL Database (`db`)**: Runs the PostgreSQL container and ensures the database is ready before starting the application.

### 2. Running the Application via Docker Compose:
- To start the application and the database using Docker Compose, follow these steps:

- Ensure Docker and Docker Compose are Installed:

- **Start the Application and Database with Docker Compose**: Run the following command to build and start the application and the database container:
    ```bash
    docker-compose up --build
    ```
  
## IV. Test API using Postman

#### 1. Folk the Postman Collection

To quickly test the API endpoints, you can fork the Postman collection for this project.

**Postman Collection Link**: [UDT eCommerce Test Collection](https://www.postman.com/satellite-astronomer-77407967/workspace/udt-ecommerce-test)

#### 2. Log in with Admin Account
After running the application, an admin account will be automatically created. You can log in with the following credentials to start testing as an admin:

`Email: admin@udt.com`

`Password: admin@udt`

As an admin, you can create **agency** accounts via `Admin` collection.

#### 3. Creating Customer Accounts
If you’d like to create a customer account, use the `Sign Up - as Customer` request in the `Authentication` Collection within Postman. 

This will allow you to test the application as a customer.




