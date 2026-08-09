# Inventory Management API

A Spring Boot-based REST API for managing products, stock items, reservations, and stock movements.

The project demonstrates a complete backend development and DevOps workflow including:

* REST API development
* Business logic for inventory management
* PostgreSQL database integration
* Flyway database migrations
* Unit testing with JUnit 5 and Mockito
* Static code analysis with SpotBugs
* Docker containerization
* Docker Compose
* GitHub Actions CI/CD
* Docker image publishing
* Kubernetes deployment
* Helm chart management
* Semantic Versioning
* Failure scenario testing and troubleshooting

---

## Features

### CRUD Operations

Full CRUD support for:

* Products
* Stock Items
* Reservations
* Movements

### Business Features

* Stock reservation and release logic
* Stock movement tracking (`IN` / `OUT`)
* Stock validation
* Insufficient stock validation
* PostgreSQL database integration
* Database schema migration with Flyway
* REST API
* Health monitoring with Spring Boot Actuator
* Docker containerization
* Kubernetes deployment
* Helm-based deployment management

---

## Tech Stack

| Technology      | Purpose                           |
| --------------- | --------------------------------- |
| Java 17         | Programming language              |
| Spring Boot     | Backend framework                 |
| Spring Data JPA | Data persistence                  |
| PostgreSQL 16   | Relational database               |
| Flyway          | Database migrations               |
| Maven           | Build and dependency management   |
| JUnit 5         | Unit testing                      |
| Mockito         | Mock-based testing                |
| SpotBugs        | Static code analysis              |
| Docker          | Containerization                  |
| Docker Compose  | Local multi-container environment |
| GitHub Actions  | CI/CD automation                  |
| Docker Hub      | Container image registry          |
| Kubernetes      | Container orchestration           |
| Helm            | Kubernetes package management     |

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

The application uses PostgreSQL 16.

Database schema changes are managed with Flyway migrations.

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

Install:

* Java 17
* Maven
* Docker Desktop
* kubectl
* Helm
* A local Kubernetes cluster such as Minikube or kind

---

## Run with Maven

Build the application:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

Application:

```text
http://localhost:8080
```

---

## Run Tests

Run all tests:

```bash
mvn test
```

The test suite includes:

* Service layer tests
* Business logic tests
* Error handling tests
* Mockito-based unit tests
* Stock reservation scenarios
* Stock release scenarios
* Stock movement scenarios
* Missing entity handling
* Insufficient stock validation

---

## Static Analysis

SpotBugs is used for static code analysis.

Run:

```bash
mvn spotbugs:check
```

The CI pipeline also executes SpotBugs automatically.

---

## Docker

### Build Docker Image

```bash
docker build -t inventory-app .
```

### Run Docker Container

```bash
docker run -p 8080:8080 inventory-app
```

Application:

```text
http://localhost:8080
```

---

## Docker Compose

Docker Compose provides a local multi-container environment containing:

* Inventory Management API
* PostgreSQL database

Start the environment:

```bash
docker compose up --build
```

Stop the environment:

```bash
docker compose down
```

---

## Environment Variables

| Variable                     | Description             |
| ---------------------------- | ----------------------- |
| `SPRING_DATASOURCE_URL`      | JDBC URL for PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | PostgreSQL username     |
| `SPRING_DATASOURCE_PASSWORD` | PostgreSQL password     |

Sensitive values should not be committed to Git.

---

## CI/CD Pipeline

The project uses GitHub Actions to automate the build, testing, analysis, Docker image creation, and image publishing workflow.

### Pipeline

```text
Git Push
   │
   ▼
GitHub Actions
   │
   ├── Checkout source
   ├── Setup Java 17
   ├── Maven build
   ├── Unit tests
   ├── SpotBugs analysis
   ├── Helm lint
   ├── Docker image build
   └── Docker image push
          │
          ▼
      Docker Hub
```

Workflow:

```text
.github/workflows/ci.yml
```

---

## Docker Image Publishing

The CI/CD pipeline builds the Docker image and publishes it to Docker Hub.

Secrets:

```text
DOCKERHUB_USERNAME
DOCKERHUB_TOKEN
```

The Kubernetes deployment must reference the same Docker repository and image tag.

---

## Kubernetes Deployment

The application can be deployed to a Kubernetes cluster.

Includes:

* Deployment
* Service
* ConfigMap
* Secret
* Readiness probe
* Rolling update strategy

Manifests:

```text
k8s/
```

---

## Kubernetes Commands

```bash
kubectl get nodes
kubectl get pods
kubectl get deployments
kubectl get services
kubectl describe pod <pod-name>
kubectl logs <pod-name>
```

---

## Helm

Helm chart:

```text
helm/inventory-chart/
```

### Validate Chart

```bash
helm lint .
```

### Install

```bash
helm install inventory .
```

### Upgrade

```bash
helm upgrade inventory .
```

### Install or Upgrade

```bash
helm upgrade --install inventory .
```

### Check Releases

```bash
helm list
```

### Status

```bash
helm status inventory
```

### History

```bash
helm history inventory
```

---

## Helm Deployment Workflow

```text
Helm Chart
    ▼
helm lint .
    ▼
helm upgrade --install inventory .
    ▼
Kubernetes Deployment
```

---

## Kubernetes Health Checks

Readiness endpoint:

```text
/actuator/health/readiness
```

---

## Failure Scenarios

### 1. Database Down
Symptoms, simulation, solution.

### 2. Health Check Failure
Symptoms, simulation, solution.

### 3. Incorrect Docker Image
Symptoms, simulation, solution.

### 4. Pod Crash
Symptoms, simulation, solution.

### 5. Incorrect Configuration
Symptoms, simulation, solution.

### 6. Service Configuration Failure
Symptoms, simulation, solution.

### 7. Helm Upgrade Failure
Symptoms, diagnosis, rollback.

---

## Kubernetes Troubleshooting Commands

```bash
kubectl get pods
kubectl describe pod <pod-name>
kubectl logs <pod-name>
kubectl get deployments
kubectl get services
kubectl get endpoints
helm status inventory
helm history inventory
```

---

## Versioning

Semantic Versioning:

```
MAJOR.MINOR.PATCH
```

Example:

```
1.6.0
```

---

## Release Workflow

```text
Code changes
Git commit
Git push
GitHub Actions
Build / Test / SpotBugs / Helm lint / Docker push
Version / Git Tag
GitHub Release
Versioned Docker Image
```

---

## Changelog

Stored in:

```text
CHANGELOG.md
```

---

## Development Workflow

```text
1. Modify application
2. Run unit tests
3. Run SpotBugs
4. Validate Helm chart
5. Build Docker image
6. Commit changes
7. Push to GitHub
8. GitHub Actions pipeline
9. Docker Hub
10. Kubernetes / Helm deployment
```

---

## Project Goals

This project demonstrates practical knowledge of:

* Java backend development
* Spring Boot
* REST API design
* Relational databases
* JPA and Hibernate
* Database migrations
* Unit testing
* Static code analysis
* Containerization
* Docker Compose
* CI/CD
* GitHub Actions
* Docker image publishing
* Kubernetes
* Helm
* Deployment troubleshooting
* Failure scenarios
* Semantic Versioning

The project combines application development and DevOps practices into a single end-to-end backend project.
