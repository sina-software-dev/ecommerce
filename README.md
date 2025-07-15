# 🛒 Simple E-Commerce System

A simple, scalable e-commerce backend application built with **Java 21** and **Spring Boot**, designed for learning and practicing enterprise-grade architecture and development.

## 📌 Features

### 👥 User Management
- Register/Login with JWT-based authentication
- Roles: `CUSTOMER`, `SUPPLIER`, `ADMIN`
- User profile management

### 🛍️ Product Management
- CRUD for products (Admin/Supplier only)
- Search, filter, pagination
- Category and stock tracking

### 🛒 Cart & Orders
- Add/remove items from cart
- Checkout and place orders
- Order history and status tracking (`PENDING`, `PAID`, `SHIPPED`, `DELIVERED`)

### 📦 Supplier Management
- Supplier registration & login
- Manage own product listings
- View orders involving their products

### 🔐 Security
- Spring Security with JWT
- Role-based access control
- Password hashing with BCrypt

---

## 🧰 Tech Stack

| Layer         | Technology              |
|---------------|-------------------------|
| Language      | Java 21                 |
| Framework     | Spring Boot 3.x         |
| Security      | Spring Security, JWT    |
| ORM           | Spring Data JPA         |
| Database      | H2                      |
| API Docs      | Swagger/OpenAPI         |
| Testing       | JUnit 5, Mockito        |
| Build Tool    | Gradle                  |
| Container     | Docker                  |
| Dev Tools     | Spring DevTools, Lombok |

---
