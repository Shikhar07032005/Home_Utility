# Home Utility Tracker

A Spring Boot application for managing home utility bills efficiently.  
This project allows users to register, log in, and track their utility bills (electricity, water, internet, etc.).

---

## 🚀 Features

- User registration and login (Spring Security)
- Manage utility bills:
  - Add, edit, delete, and mark bills as paid
  - Generate utility bill PDF reports
- Dashboard to view and track bill status
- MySQL database integration
- Responsive UI with Thymeleaf
- Docker support for containerized deployment

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Thymeleaf
- Docker

---

## 🔧 Getting Started

### Prerequisites
- Java 17+
- MySQL
- Maven
- Docker (optional)

### Build & Run Locally

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
