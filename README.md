
# Inventory Management API

Spring Boot REST API for managing products, stock items, reservations and stock movements.

This project demonstrates a complete backend + DevOps workflow:

- REST API development
- Business logic (reservations, stock movements, validation)
- PostgreSQL + Flyway migrations
- Unit testing (JUnit 5 + Mockito)
- Static analysis (SpotBugs)
- Docker (multi-stage build)
- Docker Compose
- GitHub Actions CI/CD
- Docker image publishing
- Kubernetes + Helm
- Semantic Versioning

---

## Project location

The full application is located in the `InventoryManagement/` folder:

```text
InventoryManagement/
├── src/
├── Dockerfile
├── docker-compose.yml
├── helm/
├── pom.xml
├── README.md          ← detailed documentation
└── CHANGELOG.md
```
 **Detailed documentation, API endpoints, how to run, Kubernetes & Helm instructions** are in:

**[InventoryManagement/README.md](./InventoryManagement/README.md)**

---

## Quick start

```bash
cd InventoryManagement
docker compose up --build
```

Application: `http://localhost:8080`

---

## Tech Stack

| Technology       | Purpose                        |
|------------------|--------------------------------|
| Java 17          | Language                       |
| Spring Boot      | Backend framework              |
| Spring Data JPA  | Persistence                    |
| PostgreSQL 16    | Database                       |
| Flyway           | Database migrations            |
| Docker + Compose | Containerization               |
| GitHub Actions   | CI/CD                          |
| Kubernetes + Helm| Deployment                     |

---

## Features

- Full CRUD for Products, Stock Items, Reservations and Movements
- Stock reservation & release logic
- Stock movement tracking (`IN` / `OUT`)
- Insufficient stock validation
- Health checks via Spring Boot Actuator
- Automated CI pipeline (build, test, SpotBugs, Helm lint, Docker push)

---

## Microsoft Entra ID Integration & Azure Setup

* **Spring Security:** Configured `SecurityFilterChain` to authenticate incoming JWT tokens.
* **Database:** Connection configured for the PostgreSQL container running in Azure.
* **Build & Container:** The project is compiled via Maven (`mvn clean package`) into a Java 17 JAR and containerized for deployment on Azure Container Apps.

---

## Author

**Lukas Simek**  
Junior Java Developer  
[GitHub](https://github.com/LukasDEDD) · [Website](https://lukas-simek.eu/)
