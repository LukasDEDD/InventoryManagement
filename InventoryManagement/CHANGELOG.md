# CHANGELOG.md

## [1.5.0] - 2026-08-06
### Added
- New production workflow improvements
- Updated Kubernetes manifests

### Changed
- Updated Docker image to version 2.0.0

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