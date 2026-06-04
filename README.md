# Unified Distributor Portal & AI Engine

An end-to-end enterprise system designed to manage the Order-to-Cash (O2C) lifecycle and leverage live transactional data for machine-learning-driven cross-selling.

This repository utilizes a monorepo architecture containing two core micro-services:

## 1. Order Processing API (`/order-api`)
A robust backend REST API built to handle live distributor traffic and state transitions.
* **Tech Stack:** Java 17, Spring Boot 3, Hibernate/JPA
* **Features:** * Strict business logic validation for state transitions (e.g., PENDING to SHIPPED).
  * Secure GET, POST, PATCH, and DELETE endpoints documented via OpenAPI/Swagger UI.
  * Relational data integrity enforced through PostgreSQL constraints.

## 2. B2B Recommendation Engine (`/recommendation-engine`)
An automated ETL pipeline and Collaborative Filtering AI that dynamically queries the live PostgreSQL database to generate cross-sell opportunities.
* **Tech Stack:** Python, Pandas, Scikit-Learn, psycopg2
* **Features:**
  * **ETL Pipeline:** Programmatically extracts Kaggle UCI Retail data, cleanses null/negative values, and bulk-loads 500,000+ records into the cloud database.
  * **Machine Learning:** Constructs a User-Item interaction matrix from live cloud data and applies Item-Item Cosine Similarity to predict high-probability product correlations.

## Database Architecture
Both services securely connect to a unified **Supabase Cloud PostgreSQL** instance, ensuring the AI model is always training on the latest transactional data processed by the Java API.

## API Documentation
The API is fully documented using Swagger UI. When running locally, the interactive documentation can be accessed at: `http://localhost:8080/swagger-ui/index.html`
