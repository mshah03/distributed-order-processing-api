# Distributed Order Processing API

A robust backend microservice designed to manage the Order-to-Cash (O2C) lifecycle. Built with Java and Spring Boot, this API mimics enterprise-level data traffic and state transitions.

## Tech Stack
* **Framework:** Java 17, Spring Boot 3
* **Database:** PostgreSQL (Cloud-hosted via Supabase)
* **Documentation:** OpenAPI / Swagger UI

## Key Features
* **Lifecycle Management:** Handles state transitions for active orders (e.g., PENDING to SHIPPED) with strict business logic validation.
* **RESTful Architecture:** Exposes secure GET, POST, PATCH, and DELETE endpoints.
* **Data Integrity:** Utilizes Hibernate/JPA for strict database normalization and constraint handling.

## API Documentation
The API is fully documented using Swagger UI. When running locally, the interactive documentation can be accessed at: `http://localhost:8080/swagger-ui/index.html`
