

---

# 🔗 URL Shortener – Backend API

> **A layered-monolith, SOLID, and clean-architecture–based URL shortener built with Spring Boot 3.5.5**
> Designed for clarity, scalability, and production readiness.

---

## 🚀 Deployment Info

> ✅ **Live API:**
> This backend API has been deployed to **Google Cloud Run**.
> You can access the live API here:
> Remember to test all endpoints using an API client like Postman as it's a backend API
> 🔗 [URL Shortener Service](https://url-shortening-service-585619166200.us-central1.run.app)

> 📬 **Postman Collection:**
> To test all endpoints (shorten URL and resolve alias), use the Postman collection below:
> 🔗 [URL Shortener — Postman Collection](https://www.postman.com/flight-technologist-23919603/monolithic/request/kbymaxp/url-shortener-service?action=share&creator=42910295)

---

## 🧩 Overview

This project implements a **URL Shortener backend** that follows **Clean Architecture**, **SOLID principles**, and **CS fundamentals**.
It uses **Spring Boot**, **JPA**, and **MapStruct** for clean and maintainable layering.

---

## 🧱 Architecture Overview

### ⚙️ Layered Monolith

Each functional area is self-contained under its own package but shares the same DB and runtime.
Layers follow Clean Architecture:

```
Controller → Service (Business Logic) → Repository (Persistence)
```

### 🧠 Core Design Principles

| Principle                 | Description                                                     |
| ------------------------- | --------------------------------------------------------------- |
| **Single Responsibility** | Each class has one purpose (Controller ≠ Business logic).       |
| **Open/Closed**           | Interfaces and abstractions for easy extension.                 |
| **Dependency Inversion**  | High-level modules depend on abstractions, not implementations. |
| **Encapsulation**         | DTOs hide internal entities; mapping via MapStruct.             |

---

## 📂 Project Structure

```
src/
└── main/
    ├── java/com/example/shortener/
    │   ├── config/          # DB, Redis, Cloud SQL SocketFactory
    │   ├── controller/      # REST Controllers (API layer)
    │   ├── dto/             # Request/Response DTOs
    │   ├── model/entity/    # JPA entities
    │   ├── repository/      # Spring Data Repositories
    │   ├── service/         # Interfaces + Implementations
    │   ├── util/            # Base62, Validators utils
    └── resources/
        ├── application.yml  # Environment profiles (dev/prod)
```

---

## 🧩 Core Module – Shortener

### 🗃️ Entity

```java
ShortUrl {
  Long id;
  String alias;        // unique Base62 identifier
  String targetUrl;    // original URL
  Instant createdAt;
  Instant expiresAt;   // nullable
  Boolean isActive;    // soft delete
  Long clickCount;     // updated async
}
```

### 📥 DTOs

```java
ShortenRequestDTO {
  @NotBlank String targetUrl;
}

ShortenResponseDTO {
  String alias;
  String shortUrl;
  String targetUrl;
  Instant createdAt;
}
```

### 🧠 Service Interface

```java
ShortenResponseDTO createShortUrl(ShortenRequestDTO dto);
String resolveAlias(String alias);
void incrementClickCount(String alias);
```

### 🌐 Endpoints

| Method | Endpoint           | Description                       |
| ------ | ------------------ | --------------------------------- |
| `POST` | `/api/url/shorten` | Create short URL                  |
| `GET`  | `/api/url/{alias}` | Redirect to target URL (HTTP 302) |

---

## ⚙️ Dependencies

* **Spring Boot Web**
* **Spring Data JPA** + **Hibernate Validator**
* **Lombok**
* **MapStruct** (DTO ↔ Entity mapping)
* **Spring Boot Actuator** (Health checks, metrics)

---

## 🧰 Utilities

| Utility             | Purpose                        |
| ------------------- | ------------------------------ |
| `Base62Encoder`     | Encodes DB IDs → short aliases |
| `UrlValidator`      | Validates URL format           |

---


