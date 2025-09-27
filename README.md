# Book Catalog API

This repository contains the source code for the backend API of the Book Catalog project. It is built with Java 21 and Spring Boot 3.

For a complete architectural overview of the entire ecosystem, please see the main project repository:
**[➡️ View Main Project Hub: `book-catalog`](https://github.com/DanLearnings/book-catalog)**

---

## 🚀 Development Setup

This guide explains how to run the API locally for development and testing purposes.

### Prerequisites

-   Java 21 JDK
-   Maven
-   Docker Desktop (to run the database)

### How to Run

1.  **Start the PostgreSQL database dependency:**
    A standalone PostgreSQL container is required. Run the following command in your terminal:
    ```bash
    docker run --name dev-postgres -e POSTGRES_DB=book_catalog_db -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:14-alpine
    ```

2.  **Run the Spring Boot application:**
    Open the project in your favorite IDE (like IntelliJ IDEA) and run the `BookCatalogApiApplication` class. The application will start on `http://localhost:8080`.

### Building the Project

-   **To build the JAR file:**
    ```bash
    mvn clean package
    ```
-   **To build the Docker image locally:**
    ```bash
    docker build -t book-catalog-api:local .
    ```

### CI Pipeline

A CI pipeline is configured in this repository using GitHub Actions. Every push to the `main` branch will automatically build and publish a new Docker image to the GitHub Container Registry.
