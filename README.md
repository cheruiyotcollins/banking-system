# 💳 Banking Platform Microservices

This project is a simplified banking platform built using a microservices architecture. It allows customers to manage profiles, bank accounts, make transactions, and receive notifications. It is designed for scalability, high availability, and secure inter-service communication.

---

## 🧱 Microservices Overview

| Service            | Description                                                                 |
|--------------------|-----------------------------------------------------------------------------|
| **Profile Service** | Manages customer registration, authentication (JWT), and profile updates.  |
| **Store of Value**  | Manages bank accounts — creation, update, balance inquiry, activation.      |
| **Payment Service** | Handles money transfers, top-ups, and withdrawals with transactional safety.|
| **Events Service**  | Sends notifications (SMS/Email via mocked providers) after transactions.    |
| **Config Server**   | Centralized configuration for all microservices.                            |
| **Discovery Server**| Service registry (Eureka) for inter-service discovery.                      |
| **RabbitMQ**        | Message broker used for asynchronous event delivery.                        |

---

## 📦 Getting Started

### Prerequisites

- Java 21
- Maven 3.9+
- Docker & Docker Compose

---

### ▶️ Clone the Repository

```bash
git clone https://github.com/your-username/banking-system.git
cd banking-system
```

---

### ▶️ Run with Docker Compose

```bash
docker-compose up --build
```

This will start all services including:

- Config Server
- Eureka Discovery Server
- RabbitMQ (UI: [http://localhost:15672](http://localhost:15672))
- All microservices

---

### ▶️ Run Locally (Without Docker)

You can run each service manually:

```bash
cd config-server
mvn spring-boot:run

cd discovery-server
mvn spring-boot:run

cd profile-service
mvn spring-boot:run

cd store-of-value-service
mvn spring-boot:run

cd payment-service
mvn spring-boot:run

cd events-service
mvn spring-boot:run
```

Ensure RabbitMQ is running locally or via Docker.

---

## 🔐 Security

- **JWT-based authentication**
- **Role-Based Access Control** (Admin, User roles)
- Token is passed via `Authorization: Bearer <token>`
- Optional: Enforce HTTPS for secure transport

---

## 📖 API Documentation

Each service exposes its own **Swagger UI** at:

```
http://localhost:<port>/swagger-ui/index.html
```

Replace `<port>` with the actual port of the service (defined in `application.yml` or Docker).

---

## 🧪 Testing

Each service includes:

- **JUnit 5** unit and integration tests
- **Mockito** for mocking dependencies and inter-service calls
- Edge case handling like:
  - Invalid credentials
  - Insufficient funds
  - Invalid account number
  - Concurrent transactions

To run tests:

```bash
mvn test
```

---

## 🐳 Docker & Deployment

To run the entire system:

```bash
docker-compose up --build
```

This will spin up all microservices and infrastructure. Services register with Eureka and retrieve configurations from the Config Server.

---

## 🏗️ Architecture

This system uses:

- **Spring Cloud Eureka** for service discovery
- **Spring Cloud Config Server** for centralized configuration
- **RabbitMQ** for asynchronous communication (used by the Events Service)
- **JWT + Spring Security** for secure authentication
- **PostgreSQL** (or H2 for dev/testing) as the database

A high-level architecture diagram is available in `docs/architecture-diagram.png`.

---

## ♻️ Scalability, HA, and Recovery

- **Stateless Services** → Easily horizontally scaled
- **Docker Compose / Kubernetes Ready**
- **Health Checks** via `/actuator/health`
- **Retry & Circuit Breakers** (Resilience4j - optional)
- **Separate DBs per service** to support microservice data independence
- **Message Queues** to ensure decoupled and reliable communication

---

## ↻ Data Consistency Strategy

- **Synchronous**: For profile and account lookups via REST
- **Asynchronous**: For payments and event notifications via RabbitMQ
- **Eventual Consistency**: Adopted where absolute real-time consistency is not required
- **Idempotency**: Ensured for transaction processing to avoid duplicates

---

## 🤝 Contributing

Feel free to fork the repo, raise issues, or submit pull requests.

```bash
# Typical Git workflow
git checkout -b feature/my-feature
git commit -m "Add new feature"
git push origin feature/my-feature
```

---

## 👨‍💼 Author

**Kelvin Collins Cheruiyot**  
📧 Email: cheruiyotkelvincollins@localhost  
🔗 [LinkedIn](https://linkedin.com/in/YOUR-LINK)  
🔍 [GitHub](https://github.com/your-username)

---

## 📜 License

MIT License. See [LICENSE](LICENSE) for details.

