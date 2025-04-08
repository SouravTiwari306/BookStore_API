# 📚 BookStore API

A Spring Boot-based backend service for managing books, built using a microservice-style structure with Hibernate and JPA. This project demonstrates best practices in developing RESTful APIs and is designed for learning, showcasing, and extending.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3.4**
- **Spring Data JPA**
- **Hibernate ORM**
- **Maven**
- **MySQL** (or any relational DB)
- **Lombok**
- **Spring Web**

---

## 🛠️ Features

- CRUD operations for book management  
- RESTful API endpoints  
- Hibernate-based persistence layer  
- Layered architecture (Controller, Service, Repository)  
- Exception handling and response structuring  
- Can be extended with authentication (e.g., Spring Security + JWT)

---

## 📦 How to Run the Project

### Prerequisites
- Java 17+
- Maven 3.x
- MySQL (or compatible relational DB)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Sk-SouravKumar/BookStore_API.git
   cd BookStore_API
   ```

2. **Configure your database**  
   Update `application.properties` with your local DB credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   ```

3. **Build and run the project**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API**  
   The server will start on `http://localhost:8080` (default). Use tools like Postman or Swagger to interact with the endpoints.

---

## 📘 Sample Endpoints

| Method | Endpoint             | Description         |
|--------|----------------------|---------------------|
| GET    | `/books`             | List all books      |
| POST   | `/books`             | Add a new book      |
| PUT    | `/books/{id}`        | Update book details |
| DELETE | `/books/{id}`        | Remove a book       |

---

## 🤝 Collaboration

This project is intended for  portfolio use, demonstrating backend development and Spring Boot expertise. Collaboration, contributions, and extensions (like auth, pagination, etc.) are welcome!

---

## 👤 Author

**Sourav Kumar**  
[GitHub Profile](https://github.com/Sk-SouravKumar)

