# Restro Mini Project

This is a Spring Boot application integrated with PostgreSQL, designed to manage restaurant-related functionalities. The project supports features such as user registration, authentication, feedback management, and password recovery.

## Project Overview

The **Restro Mini Project** is aimed at providing a backend service for restaurant management. It offers various functionalities including:

- **User Registration:** Allows users to sign up with a unique email, username, and phone number.
- **User Authentication:** Supports secure login functionality.
- **Feedback System:** Collects and stores feedback for both the site and specific restaurants.
- **Forgot Password:** Users can reset their passwords through an email verification process.

## Features

- **User Registration and Login**
- **Feedback Collection for Restaurants and the Website**
- **Password Recovery Mechanism**

## Docker Image

The application is containerized using Docker, making it easy to deploy and run on any system that supports Docker.

- **Image Name:** `suyash30/restro-mini-project`
- **Version:** `1.2`
- **Repository:** [Docker Hub](https://hub.docker.com/r/suyash30/restro-mini-project)

## Prerequisites

To run this project, ensure that the following are installed on your system:

- **Docker**
- **Docker Compose**

## Running the Application

Follow the steps below to run the application using Docker:

1. **Pull the Docker Image:**

   ```bash
   docker pull suyash30/restro-mini-project:1.2 
  
2. **Set Up and Start Services:**

   Create a directory for PostgreSQL data and navigate to it:

   ```bash
   mkdir -p /path/to/your/project/postgres_data

Create a docker-compose.yml file in your working directory with the content provided in this repository.

Start the services using Docker Compose:
   ```bash
   docker-compose up
   ```

## Configuration
 -**Database URL:** Update the database URL in the application-uat.yml file or the docker-compose.yml to match your PostgreSQL instance.
 -**Ports:** Adjust the ports in the docker-compose.yml file if necessary to avoid conflicts.

### Sample docker-compose.yml Configuration
   ```yaml
   version: '3.8'

services:
  db:
    image: postgres
    environment:
      POSTGRES_USER: myuser
      POSTGRES_PASSWORD: mypassword
      POSTGRES_DB: mydb
    ports:
      - "5432:5432"
    volumes:
      - "/path/to/your/project/postgres_data:/var/lib/postgresql/data"
  app:
    image: suyash30/restro-mini-project:latest
    environment:
      SPRING_PROFILES_ACTIVE: uat
      SPRING_DATASOURCE_URL: jdbc:postgresql://your-db-url:5432/mydb
      SPRING_DATASOURCE_USERNAME: myuser
      SPRING_DATASOURCE_PASSWORD: mypassword
    ports:
      - "8080:8081"
    depends_on:
      - db
   ```

# Troubleshooting
**Database Connection Issues:** Ensure the PostgreSQL service is running and accessible.
**Permissions Issues:** Verify that the PostgreSQL data directory is writable by Docker.
**Port Conflicts:** If the default ports are in use, modify the ports in the docker-compose.yml file.

# Documentation
For a detailed setup guide and additional information, refer to the full [documentation.](https://docs.google.com/document/d/e/2PACX-1vRNvN6bOsuAup-XzFhLKK_OOJgrHQAD_bBeiiqPiw_-fWPvSUJF5R6csEhjyy1f_B9wHQQqv8eiQ0S-/pub)

## Contact

For support or questions, please reach out to the project maintainer via Docker Hub or through the following contact details:

- **Email:** [shevadesuyash30@gmail.com](shevadesuyash30@gmail.com)
- **LinkedIn:** [Suyash Shevade](https://www.linkedin.com/in/suyash-shevade-8b07a9236/)
- **GitHub:** [Shevadesuyash](https://github.com/Shevadesuyash)

This project is maintained by **Suyash Shevade**.





























  
