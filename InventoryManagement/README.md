# Inventory Management API

A Spring Boot-based REST API for managing products, stock items, reservations, and stock movements.

The project includes full CRUD operations, business logic for stock handling, PostgreSQL integration, Docker support, and a CI/CD pipeline using GitHub Actions.

---

# Features

## CRUD Operations

Full CRUD support for:

- Products
- Stock Items
- Reservations
- Movements

## Business Features

- Stock reservation and release logic
- Stock movement tracking (`IN` / `OUT`)
- PostgreSQL database integration
- Docker & Docker Compose support
- GitHub Actions CI pipeline
- Unit tests with Mockito
- SpotBugs static analysis

---

# Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL 16
- Maven
- Docker / Docker Compose
- GitHub Actions
- JUnit 5
- Mockito

---

# Project Structure

```text
InventoryManagement/
│
├── src/
│   ├── main/
│   │   └── java/com/example/InventoryManagement
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── exception/
│   │       ├── mapper/
│   │       ├── repository/
│   │       └── service/
│   │
│   └── test/
│       └── java/com/example/InventoryManagement
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
└── CHANGELOG.md
```

# API Endpoints

## Products

```http
POST    /api/products              Create product

GET     /api/products              Get all products

GET     /api/products/{id}         Get product by ID

PUT     /api/products/{id}         Update product

DELETE  /api/products/{id}         Delete product

GET     /api/products/{id}/exists  Check existence

GET     /api/products/count        Count products
```

## Stock Items

```http
POST    /api/stock-items

GET     /api/stock-items

GET     /api/stock-items/{id}

PUT     /api/stock-items/{id}

DELETE  /api/stock-items/{id}

GET     /api/stock-items/{id}/exists

GET     /api/stock-items/count
```

## Reservations

```http
POST    /api/reservations

GET     /api/reservations

GET     /api/reservations/{id}

PUT     /api/reservations/{id}

DELETE  /api/reservations/{id}

GET     /api/reservations/{id}/exists

GET     /api/reservations/count

POST    /api/reservations/reserve?productId=&quantity=

POST    /api/reservations/release?productId=&quantity=
```

## Movements

```http
POST    /api/movements

GET     /api/movements

GET     /api/movements/{id}

PUT     /api/movements/{id}

DELETE  /api/movements/{id}

GET     /api/movements/{id}/exists

GET     /api/movements/count

POST    /api/movements/move?productId=&quantity=
```

---

## Running Locally

### Prerequisites

- Java 17
- Maven
- Docker (optional but recommended)

---

## Run with Maven

Build project:

```bash
mvn clean install
```

Start application:

```bash
mvn spring-boot:run
```

Application runs at:

```text
http://localhost:8080
```

---

## Run with Docker

Build Docker Image:

```bash
docker build -t inventory-app .
```

Run Container:

```bash
docker run -p 8080:8080 inventory-app
```

---

## Run with Docker Compose

```bash
docker-compose up --build
```

Starts:

- PostgreSQL database
- Inventory Management API

---

# Environment Variables

| Variable | Description |
|---|---|
| `SPRING_DATASOURCE_URL` | JDBC URL for PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | Database username |
| `SPRING_DATASOURCE_PASSWORD` | Database password |

---

# CI/CD Pipeline (GitHub Actions)

The project uses GitHub Actions for continuous integration and delivery.

Pipeline includes:

- Code checkout
- Java setup
- Maven build
- Unit tests execution
- SpotBugs static analysis
- Docker image build
- Automatic Docker Hub push on `master`

Workflow file:

```text
.github/workflows/ci.yml
```

---

# Testing

Run all tests:

```bash
mvn test
```

Tests include:

- Service layer tests
- Error handling tests
- Business logic tests
- Mockito-based unit tests

Covered business scenarios:

- Stock reservation
- Stock release
- Stock movement (`IN` / `OUT`)
- Missing entity handling
- Insufficient stock validation

---

# Versioning

This project follows Semantic Versioning:

```text
MAJOR.MINOR.PATCH
```

Example:

```text
1.4.0
```

Version history is available in:

[CHANGELOG.md](CHANGELOG.md)

---




