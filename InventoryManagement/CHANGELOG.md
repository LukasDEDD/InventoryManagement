# CHANGELOG.md

## [1.7.0] – Microsoft Entra ID Security & Azure Container Apps Deployment

### Added
- Integrated Microsoft Entra ID (Azure AD) authentication using OAuth2 Resource Server.
- Configured PostgreSQL database container and integration for Azure deployment.
- Containerized Java JAR application and configured deployment to Azure Container Apps.
- Set up Spring Security filter chain to secure REST API endpoints.
- Added environment variables for database connection and Entra ID (`AZURE_TENANT_ID`, `AZURE_CLIENT_ID`).
- Exposed public health endpoints under `/actuator/health`.


## [1.6.1] - 2026-08-10

### Fixed
- **Spring Boot Actuator:** Added `spring-boot-starter-actuator` dependency to `pom.xml` to enable health checks.
- **Kubernetes Probes:** Fixed `livenessProbe` and `readinessProbe` in the Helm chart to properly target the `/actuator/health` endpoint.
- **Resource Limits:** Increased container memory limits in `values.yaml` to `1Gi` to ensure smooth application startup and prevent health probe timeouts.

---

## [1.6.0] – CI Pipeline Stabilization & Helm Workflow Cleanup ##
### Added
- Added Helm linting step to CI pipeline
- Introduced automated changelog generation for release workflow
- Added improved Kubernetes service and deployment manifests

### Changed
- CI pipeline updated after migration from Podman to Docker Desktop
- Refactored deployment structure and moved Kubernetes manifests to unified Helm chart
- Updated build and push logic for Docker images

### Fixed
- Fixed inconsistent CI job ordering
- Resolved minor YAML formatting issues in Kubernetes manifests
- Improved reliability of Docker image publishing in GitHub Actions

---

## [1.5.0] - Kubernetes Deployment & Production Workflow Updates
### Added
- New production workflow improvements
- Updated Kubernetes manifests

### Changed
- Updated Docker image to version 1.5.0

### Fixed
- Minor CI warnings


## [1.4.0] – CI/CD Pipeline Improvements

### Added

GitHub Actions workflow:

- Automatic builds on `master`
- PostgreSQL service for integration tests
- SpotBugs scanning
- Docker image build
- Docker Hub push

### Changed

- Maven build switched to `mvn clean verify`.
- Build context updated to `./InventoryManagement`.

---

## [1.3.0] – API Extensions

### Added

Existence endpoints:

- `/api/products/{id}/exists`
- `/api/stock-items/{id}/exists`
- `/api/reservations/{id}/exists`
- `/api/movements/{id}/exists`

Count endpoints:

- Added count endpoints for all modules.

Reservation operations:

- `/api/reservations/release`
- `/api/reservations/reserve`

Movement operation:

- `/api/movements/move`

---

## [1.2.0] – Stock & Reservation Logic Improvements

### Added

`ReservationService.reserveStock(productId, quantity)`:

- Validates stock availability
- Deducts stock
- Creates reservation

`ReservationService.releaseStock(productId, quantity)`:

- Increases stock
- Updates reservation status to `CANCELLED`

`MovementService.moveStock(productId, quantity)`:

- Increases stock
- Cancels reservation

### Fixed

Improved error handling for:

- Missing products
- Missing stock items
- Missing reservations
- Insufficient stock

---

## [1.1.0] – Domain Model Enhancements

### Added

New entity fields:

- `Movement.createdAt`
- `Reservation.createdAt`
- `StockItem.updatedAt`

New enums:

- `MovementType { IN, OUT }`
- `ReservationStatus { ACTIVE, CANCELLED, COMPLETED }`

### Changed

- Updated service logic to support new timestamps and enum values.
- Updated mappers to handle new fields.

---

## [1.0.0] – Initial Release

### Added

Complete Spring Boot-based Inventory Management API.

CRUD operations for:

- Products
- Stock Items
- Reservations
- Movements

DTO layer:

- Added DTO layer for all domain entities.

Mapping layer:

- `ProductMapper`
- `StockItemMapper`
- `ReservationMapper`
- `MovementMapper`

Repository layer:

- Repository layer using Spring Data JPA.

Service layer with core business logic:

- Stock reservation
- Stock release
- Stock movement (`IN` / `OUT`)

REST API:

- REST controllers for all modules.

Exception handling:

- Global error handling using `ResourceNotFoundException`.

---

### Infrastructure

Database:

- PostgreSQL 16 database (Dockerized).

Docker:

- Multi-stage Dockerfile:
    - Maven build stage
    - Temurin JRE runtime stage

- `docker-compose.yml` for local development:
    - Spring Boot application
    - PostgreSQL database

CI/CD:

GitHub Actions CI Pipeline:

- Maven build & tests
- SpotBugs static analysis
- Docker image build
- Automatic push to Docker Hub on `master`

---

### Testing

Unit tests for:

- `ProductService`
- `StockItemService`
- `ReservationService`
- `MovementService`

Testing:

- Mockito-based mocking with `@ExtendWith(MockitoExtension.class)`

Test coverage includes:

- CRUD operations
- Error handling
- Business logic for:
    - Stock reservation
    - Stock release
    - Stock movement