🧩 Java Microservices Assignment – Inventory & Order Service

This repository contains a Spring Boot–based microservices project built as part of a Java backend assignment.
The system simulates a simplified e-commerce backend with inventory management and order processing, implemented using Java 8 and modern Spring practices.

🚀 Overview

The project consists of two independent microservices:

🔹 Inventory Service

Manages product inventory

Supports multiple inventory batches per product

Each batch has an expiry date

Inventory is always processed in expiry-date order

Uses Factory Design Pattern to allow future extensibility

Loads schema and sample data using Liquibase + CSV

🔹 Order Service

Accepts product orders

Communicates with Inventory Service via REST

Reserves inventory before placing an order

Stores order details in its own database

Each service:

Runs independently

Has its own database

Communicates synchronously using REST APIs

🛠️ Tech Stack

Java: 8

Framework: Spring Boot 2.7.x

Database: H2 (In-Memory)

ORM: Spring Data JPA (Hibernate)

DB Migration: Liquibase

Build Tool: Maven

Testing: JUnit 5, Mockito

Inter-service Communication: RestTemplate

📁 Project Structure
koerber-java-microservices/
│
├── inventory-service/
│   ├── src/main/java/com/koerber/inventory
│   │   ├── controller
│   │   ├── service
│   │   ├── factory
│   │   ├── repository
│   │   └── entity
│   ├── src/main/resources
│   │   ├── application.yml
│   │   ├── inventory.csv
│   │   └── db/changelog
│   └── pom.xml
│
├── order-service/
│   ├── src/main/java/com/koerber/order
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── entity
│   │   └── dto
│   ├── src/main/resources
│   │   └── db/changelog
│   └── pom.xml
│
└── README.md

▶️ How to Run the Project
✅ Prerequisites

Java 8

Maven

Git

1️⃣ Run Inventory Service
cd inventory-service
mvn clean spring-boot:run


Runs on: http://localhost:8081

Liquibase automatically:

Creates tables

Loads inventory data from CSV

2️⃣ Run Order Service (new terminal)
cd order-service
mvn clean spring-boot:run


Runs on: http://localhost:8082

🔗 API Endpoints
Inventory Service
Get inventory by product (sorted by expiry date)
GET /inventory/{productId}


Example:

GET http://localhost:8081/inventory/1005

Update inventory (used internally)
POST /inventory/update?productId={id}&quantity={qty}

Order Service
Place an order
POST /order


Request:

{
  "productId": 1005,
  "quantity": 2
}


Response:

{
  "orderId": 1,
  "productId": 1005,
  "productName": "AUTO_FETCHED",
  "quantity": 2,
  "status": "PLACED",
  "message": "Order placed. Inventory reserved."
}

🧪 Testing

Unit tests written using JUnit 5 + Mockito

Repository and REST dependencies are mocked

Tests can be run using:

mvn test


Integration testing can be done using:

@SpringBootTest

H2 in-memory database

🗄️ H2 Console (Optional)
Inventory DB
http://localhost:8081/h2-console
JDBC URL: jdbc:h2:mem:inventorydb
User: sa
Password: (empty)

Order DB
http://localhost:8082/h2-console
JDBC URL: jdbc:h2:mem:orderdb
User: sa
Password: (empty)

✅ Assignment Highlights

✔ Two independent microservices
✔ REST-based communication
✔ Factory Design Pattern
✔ Liquibase for schema & data loading
✔ Java 8 compatible
✔ Clean layered architecture
✔ Unit testing with Mockito
✔ Git-ready project structure

👤 Author

Akash Pandey
Java Backend Developer


Review your GitHub repo link once pushed

Just tell me 👍
