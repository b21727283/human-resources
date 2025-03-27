# Human Resources Application

## Table of Contents
- [Overview](#overview)
- [Backend](#backend)
    - [Running the Application](#running-the-application)
    - [Database Configuration](#database-configuration)
    - [API Endpoints](#api-endpoints)
- [Frontend](#frontend)
    - [Running the Application](#running-the-application-1)

## Overview
This project is a Human Resources application built with Java, Spring Boot, and Maven for the backend, and Angular with the Ionic framework for the frontend.

## Backend

### Running the Application
1. Ensure you have Java and Maven installed.
2. Navigate to the backend directory.
3. Run the following command to clean and install dependencies:
   ```sh
   mvn clean install
   ```
4. Run the following command to start the application:
   ```sh
   mvn spring-boot:run
   ```

### Database Configuration
The application uses a SQL database. Configure the database connection in the `application.properties` file located in the `src/main/resources` directory.

Example configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/human_resources
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### API Endpoints
#### Employee API
- **GET /employee/get/all**
  - **Request:** None
  - **Response:** List of all employees
  ```json
  [
    {
      "id": 1,
      "name": "John Doe",
      "position": "Developer"
    }
  ]
  ```

- **GET /employee/get/{employeeId}**
  - **Request:** None
  - **Response:** Employee details
  ```json
  {
    "id": 1,
    "name": "John Doe",
    "position": "Developer"
  }
  ```

- **POST /employee/create**
  - **Request:** `multipart/form-data`
  ```json
  {
    "name": "Jane Doe",
    "surname": "Doe",
    "position": "Manager",
    "militaryStatus": "EXEMPT",
    "noticePeriod": "2 weeks",
    "phoneNumber": "1234567890",
    "email": "jane.doe@example.com",
    "cv": "file"
  }
  ```
  - **Response:** Created employee
  ```json
  {
    "id": 2,
    "name": "Jane Doe",
    "surname": "Doe",
    "position": "Manager",
    "militaryStatus": "EXEMPT",
    "noticePeriod": "2 weeks",
    "phoneNumber": "1234567890",
    "email": "jane.doe@example.com"
  }
  ```

- **PUT /employee/update/{employeeId}**
  - **Request:** `multipart/form-data`
  ```json
  {
    "name": "John Smith",
    "surname": "Smith",
    "position": "Senior Developer",
    "militaryStatus": "COMPLETED",
    "noticePeriod": "1 month",
    "phoneNumber": "0987654321",
    "email": "john.smith@example.com",
    "cv": "file"
  }
  ```
  - **Response:** Updated employee
  ```json
  {
    "id": 1,
    "name": "John Smith",
    "surname": "Smith",
    "position": "Senior Developer",
    "militaryStatus": "COMPLETED",
    "noticePeriod": "1 month",
    "phoneNumber": "0987654321",
    "email": "john.smith@example.com"
  }
  ```

- **DELETE /employee/delete/{employeeId}**
  - **Request:** None
  - **Response:** Deleted employee information
  

- **GET /employee/{id}/cv**
  - **Request:** None
  - **Response:** Employee CV file

## Frontend

### Running the Application
1. Ensure you have Node.js and npm installed.
2. Navigate to the frontend directory.
3. Run the following commands to install dependencies and start the application:
   ```sh
   npm install
   ionic serve
   ```
4. Open your browser and navigate to `http://localhost:8100`.