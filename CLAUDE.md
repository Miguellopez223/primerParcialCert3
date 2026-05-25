# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build entire project (from root)
./mvnw clean install

# Run the application (Spring Boot)
./mvnw spring-boot:run -pl eventop-api

# Run tests
./mvnw test

# Run tests for a single module
./mvnw test -pl core
./mvnw test -pl data
./mvnw test -pl eventop-api
```

On Windows use `mvnw.cmd` instead of `./mvnw`.

## Prerequisites

- Java 17
- PostgreSQL database: `eventop_db` on `localhost:5432` (user: `eventop_user`, password: `123456`)
- The app runs on port **8081**

## Architecture

Multi-module Maven project (Spring Boot 4.0.6) with three layers:

```
eventop (parent pom)
├── data          — JPA entities, repositories, DTOs, enums
├── core          — Business services (depends on data)
└── eventop-api   — REST controllers, security config, Spring Boot app (depends on core)
```

**Dependency flow:** `eventop-api → core → data`

### Domain Model

- **User** — app users with roles (ROLE_ROOT) and statuses (ACTIVO). Implements Spring Security's UserDetails.
- **Empresa** — companies that organize events. UUID primary keys.
- **Eventos** — events belonging to an Empresa (ManyToOne). UUID primary keys.
- **AuditableEntity** — base class for audit fields.

### Security

- JWT-based stateless auth using jjwt library.
- `POST /api/v1/auth` — login endpoint (public).
- `JwtTokenProvider` creates/validates tokens; `JwtTokenFilter` intercepts requests.
- On first startup, a root user is seeded via `DataInitializer` (username: `root`, password: `Abc123**`).

### API Endpoints

All under `/api/v1/`:
- `POST /auth` — authenticate and receive JWT (public)
- `GET/POST/PUT /empresas` — CRUD for companies (public)
- `EventoController` — event operations (authenticated)

### Key Conventions

- Lombok is used project-wide (`@Slf4j`, `@AllArgsConstructor`, `@RequiredArgsConstructor`, `@Builder`).
- Entities use `@UuidGenerator` for ID generation.
- DTOs are Java records (e.g., `AuthenticationDto`, `EmpresaRequestDto`).
- Hibernate ddl-auto is set to `update` (schema auto-managed).
- Code and comments are in Spanish.
