

# Inventory Management API

A Spring Boot-based REST API for managing products, stock items, reservations, and stock movements.

The project demonstrates a complete backend development and DevOps workflow including:

- REST API development
- Business logic for inventory management
- PostgreSQL database integration
- Flyway database migrations
- Unit testing with JUnit 5 and Mockito
- Static code analysis with SpotBugs
- Docker containerization
- Docker Compose
- GitHub Actions CI/CD
- Docker image publishing
- Kubernetes deployment
- Helm chart management
- Semantic Versioning
- Failure scenario testing and troubleshooting

---

## Features

### CRUD Operations

Full CRUD support for:

- Products
- Stock Items
- Reservations
- Movements

### Business Features

- Stock reservation and release logic
- Stock movement tracking (`IN` / `OUT`)
- Stock validation
- Insufficient stock validation
- PostgreSQL database integration
- Database schema migration with Flyway
- REST API
- Health monitoring with Spring Boot Actuator
- Docker containerization
- Kubernetes deployment
- Helm-based deployment management

---

## Tech Stack

| Technology       | Purpose                            |
|------------------|------------------------------------|
| Java 17          | Programming language               |
| Spring Boot      | Backend framework                  |
| Spring Data JPA  | Data persistence                   |
| PostgreSQL 16    | Relational database                |
| Flyway           | Database migrations                |
| Maven            | Build and dependency management    |
| JUnit 5          | Unit testing                       |
| Mockito          | Mock-based testing                 |
| SpotBugs         | Static code analysis               |
| Docker           | Containerization                   |
| Docker Compose   | Local multi-container environment  |
| GitHub Actions   | CI/CD automation                   |
| Docker Hub       | Container image registry           |
| Kubernetes       | Container orchestration            |
| Helm             | Kubernetes package management      |

---

## Project Structure

```text
InventoryManagement/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/InventoryManagement/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── mapper/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/
│   │           └── migration/
│   │
│   └── test/
│       └── java/com/example/InventoryManagement/
│
├── k8s/
│   ├── deployment.yaml
│   ├── service.yaml
│   ├── configmap.yaml
│   └── secret.yaml
│
├── helm/
│   └── inventory-chart/
│       ├── Chart.yaml
│       ├── values.yaml
│       ├── templates/
│       │   ├── deployment.yaml
│       │   ├── service.yaml
│       │   ├── configmap.yaml
│       │   ├── secret.yaml
│       │   └── _helpers.tpl
│       └── .helmignore
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
└── CHANGELOG.md
```

---

## API Endpoints

### Products

```http
POST    /api/products
GET     /api/products
GET     /api/products/{id}
PUT     /api/products/{id}
DELETE  /api/products/{id}
GET     /api/products/{id}/exists
GET     /api/products/count
```

### Stock Items

```http
POST    /api/stock-items
GET     /api/stock-items
GET     /api/stock-items/{id}
PUT     /api/stock-items/{id}
DELETE  /api/stock-items/{id}
GET     /api/stock-items/{id}/exists
GET     /api/stock-items/count
```

### Reservations

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

### Movements

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

## Database

The application uses **PostgreSQL 16**.

Database schema changes are managed with **Flyway** migrations.

```text
src/main/resources/db/migration/
```

Example migration structure:

```text
V1__init.sql
V2__...
V3__...
```

Flyway applies migrations automatically when the application starts.

---

## Running Locally

### Prerequisites

- Java 17
- Maven
- Docker Desktop
- kubectl
- Helm
- Local Kubernetes cluster (Minikube or kind)

### Run with Maven

```bash
mvn clean install
mvn spring-boot:run
```

Application: `http://localhost:8080`

### Run Tests

```bash
mvn test
```

The test suite includes:

- Service layer tests
- Business logic tests
- Error handling tests
- Mockito-based unit tests
- Stock reservation / release / movement scenarios
- Missing entity handling
- Insufficient stock validation

### Static Analysis

```bash
mvn spotbugs:check
```

---

## Docker

### Build image

```bash
docker build -t inventory-app .
```

### Run container

```bash
docker run -p 8080:8080 inventory-app
```

### Docker Compose

```bash
docker compose up --build
docker compose down
```

Starts:

- Inventory Management API
- PostgreSQL database

---

## Environment Variables

| Variable                     | Description             |
|------------------------------|-------------------------|
| `SPRING_DATASOURCE_URL`      | JDBC URL for PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | PostgreSQL username     |
| `SPRING_DATASOURCE_PASSWORD` | PostgreSQL password     |

Sensitive values should not be committed to Git.

---

## CI/CD Pipeline

GitHub Actions automatically runs:

```text
Git Push
   │
   ▼
GitHub Actions
   ├── Checkout source
   ├── Setup Java 17
   ├── Maven build
   ├── Unit tests
   ├── SpotBugs analysis
   ├── Helm lint
   ├── Docker image build
   └── Docker image push → Docker Hub
```

Workflow file: `.github/workflows/ci.yml`

**Required secrets:**

- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN`

---

## Kubernetes

### Manifests

```text
k8s/
├── deployment.yaml
├── service.yaml
├── configmap.yaml
└── secret.yaml
```

Includes:

- Deployment
- Service
- ConfigMap
- Secret
- Readiness probe
- Rolling update strategy

### Useful commands

```bash
kubectl get nodes
kubectl get pods
kubectl get deployments
kubectl get services
kubectl describe pod <pod-name>
kubectl logs <pod-name>
```

### Health check

```text
/actuator/health/readiness
```

---

## Helm

Chart location: `helm/inventory-chart/`

```bash
helm lint .
helm install inventory .
helm upgrade inventory .
helm upgrade --install inventory .
helm list
helm status inventory
helm history inventory
```

---

## Security & Authentication

The application uses **Microsoft Entra ID (Azure AD)** for securing REST API endpoints via OAuth2 Resource Server and JWT validation.

* **Protected Endpoints:** All REST API endpoints require a valid Bearer token.
* **Public Endpoints:** `/actuator/health`, `/swagger-ui/**`, `/v3/api-docs/**`

### Environment Variables

To run the application in Azure Container Apps (or locally), ensure the following environment variables are set:

* `AZURE_TENANT_ID` – Microsoft Entra ID tenant ID
* `AZURE_CLIENT_ID` – Application registration Client ID
* `SPRING_DATASOURCE_URL` – JDBC URL for the Azure PostgreSQL database
* `SPRING_DATASOURCE_USERNAME` – Database username
* `SPRING_DATASOURCE_PASSWORD` – Database password

---

## Deployment (Azure Container Apps)

The application is compiled into an executable JAR file, packaged into a Docker container, and deployed to **Azure Container Apps** along with a PostgreSQL database container.

---

## Failure Scenarios

1. Database Down
2. Health Check Failure
3. Incorrect Docker Image
4. Pod Crash
5. Incorrect Configuration
6. Service Configuration Failure
7. Helm Upgrade Failure

(Each scenario includes symptoms, simulation and solution.)

---

## Versioning

Semantic Versioning: `MAJOR.MINOR.PATCH`  
Example: `1.6.0`

---

## Changelog

See `CHANGELOG.md`

---

## Project Goals

This project demonstrates practical knowledge of:

- Java backend development
- Spring Boot & REST API design
- Relational databases (JPA / Hibernate)
- Database migrations (Flyway)
- Unit testing & static analysis
- Containerization (Docker + Compose)
- CI/CD (GitHub Actions)
- Kubernetes & Helm
- Deployment troubleshooting
- Semantic Versioning

The project combines application development and DevOps practices into a single end-to-end backend project.
