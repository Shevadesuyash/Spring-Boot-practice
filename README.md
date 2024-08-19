# Restro Mini Project

This is a Spring Boot application integrated with PostgreSQL, designed to manage restaurant-related functionalities. The project supports features such as user registration, authentication, feedback management, and password recovery.

## Project Overview

The **Restro Mini Project** is aimed at providing a backend service for restaurant management. It offers various functionalities including:

- **User Registration:** Allows users to sign up with a unique email, username, and phone number.
- **User Authentication:** Supports secure login functionality.
- **Feedback System:** Collects and stores feedback for both the site and specific restaurants.
- **Forgot Password:** Users can reset their passwords through an email verification process.

## Features

### User Management
* User registration
* User login
* Password reset (not implemented)

### Restaurant Management
* Restaurant registration
* Restaurant details update
* Restaurant deletion
* Retrieve all restaurant details

### Feedback System
* Submit site feedback
* Submit restaurant feedback
* Retrieve all site feedback
* Retrieve all restaurant feedback

### API Documentation
* Swagger UI integration


## Installation

This project requires Java, Maven, and a PostgreSQL database.

**Prerequisites:**

* Java 21
* Maven
* PostgreSQL

**Steps:**

1. Clone the repository:

   ```bash
   git clone https://github.com/Shevadesuyash/Spring-Boot-practice.git
   cd Spring-Boot-practice

2. Configure your PostgreSQL database settings in the appropriate application-*.yml file located under src/main/resources (dev, prod, or uat).

3. Build the project:
   ```bash
   mvn clean install
4. Run the application:
   ```bash
   mvn spring-boot:run

## Usage

**Running Application:**

Once running, access these endpoints:

* **User Registration:** `POST /users/register`
  * Body: User registration details (model: `UserRegistrationRequest`)
* **User Login:** `POST /users/login`
  * Body: User login credentials (model: `LoginRequest`)
* **Get User Details:** `GET /users/details`
* **Forgot Password:** `POST /users/forgot-password`
  * Body: User credentials for password reset (model: `ForgotPassword`)
* **Submit Site Feedback:** `POST /feedback/site_feedback`
  * Body: Site feedback details (model: `FeedbackSiteRequest`)
* **Submit Restaurant Feedback:** `POST /feedback/restro_feedback`
  * Body: Restaurant feedback details (model: `FeedbackRestroRequest`)
* **Get All Restaurant Feedback:** `GET /feedback/feedback-restro`
* **Get All Site Feedback:** `GET /feedback/feedback-site`
* **Delete Restaurant:** `DELETE /restro/delete`
  * Body: Restaurant ID (model: `DeleteRequest`)
* **Update Restaurant Details:** `PUT /restro/updateDetails`
  * Body: Updated restaurant details (model: `RestroOnlineRequestUpdate`)
* **Add New Restaurant:** `POST /restro/addNew`
  * Body: New restaurant details (model: `RestroOnlineRequest`)
* **Get All Restaurant Details:** `GET /restro/allRestro`

**Note:** This is a basic overview of the endpoints. Refer to the project documentation for detailed information about request and response structures, error handling, and additional features.

## Configuration

* Application configuration is handled via YAML files for different environments (dev, prod, uat) located under `src/main/resources`.
* Swagger configuration is handled through the `AppConfig` class for API documentation.

## Dependencies

* Spring Boot Starter Data JPA (database interactions)
* Spring Boot Starter Web (web application building)
* Lombok (reduced boilerplate code)
* PostgreSQL (database)
* Springdoc OpenAPI (Swagger integration)


**I have created a Docker image for this project, allowing anyone to run the application without the need for manual setup.**




## Docker Image

This project provides a convenient Docker image for simplified deployment and execution without extensive manual setup.

**Image Details:**

* **Name:** `suyash30/restro-mini-project`
* **Version:** `1.2` (Replace with the actual version)
* **Repository:** [Docker Hub](https://hub.docker.com/r/suyash30/restro-mini-project/tags)

**Prerequisites:**

* **Docker:** Ensure Docker is installed on your system. Refer to the official documentation for installation instructions: [https://docs.docker.com/engine/install/](https://docs.docker.com/engine/install/)
* **Docker Compose:** Install Docker Compose for streamlined multi-container management: [https://docs.docker.com/compose/install/](https://docs.docker.com/compose/install/)







## Running the Application

Follow the steps below to run the application using Docker:

### 1. Pull the Docker Image:

   ```bash
   docker pull suyash30/restro-mini-project:1.3  # Replace with the actual version
  ```
### 2. Set Up and Start Services:

**a. Create a directory for PostgreSQL data and navigate to it:**
   ```bash
   mkdir -p /path/to/your/project/postgres_data  # Replace with your desired location
   ```
**b. Create a `docker-compose.yml` File**

Create a file named `docker-compose.yml` in your working directory. You can use the following sample configuration as a starting point, customizing it as needed:

```yaml
version: '3.8'

services:
  db:
    image: postgres
    environment:
      POSTGRES_USER: myuser  # Replace with your desired username
      POSTGRES_PASSWORD: mypassword  # Replace with your desired password
      POSTGRES_DB: mydb  # Replace with your desired database name
    ports:
      - "5432:5432"  # Map the container port to your host machine port (5432 is the default)
    volumes:
      - "/path/to/your/project/postgres_data:/var/lib/postgresql/data"  # Adjust the path for persistent data storage

  app:
    image: suyash30/restro-mini-project:1.3  # Replace with the actual version
    environment:
      SPRING_PROFILES_ACTIVE: uat  # Adjust the environment profile if needed
      # Update the following environment variables according to your database configuration
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/mydb
      SPRING_DATASOURCE_USERNAME: myuser
      SPRING_DATASOURCE_PASSWORD: mypassword
    ports:
      - "8080:8081"  # Map the container port (8081) to your desired host machine port (8080)
    depends_on:
      - db
```
***c. Start the Services:***

    docker-compose up
  

# Troubleshooting
- **Database Connection Issues:** Ensure the PostgreSQL service is running and accessible.
- **Permissions Issues:** Verify that the PostgreSQL data directory is writable by Docker.
- **Port Conflicts:** If the default ports are in use, modify the ports in the docker-compose.yml file.

# Documentation
For a detailed setup guide and additional information, refer to the full [documentation.](https://docs.google.com/document/d/e/2PACX-1vRNvN6bOsuAup-XzFhLKK_OOJgrHQAD_bBeiiqPiw_-fWPvSUJF5R6csEhjyy1f_B9wHQQqv8eiQ0S-/pub)

## Contact

For support or questions, please reach out to the project maintainer via Docker Hub or through the following contact details:

- **Email:** [shevadesuyash30@gmail.com](shevadesuyash30@gmail.com)
- **LinkedIn:** [Suyash Shevade](https://www.linkedin.com/in/suyash-shevade-8b07a9236/)
- **GitHub:** [Shevadesuyash](https://github.com/Shevadesuyash)
- **Instagram** [shevadesuyash30](https://www.instagram.com/shevadesuyash30/)

This project is maintained by **Suyash Shevade**.





























  
