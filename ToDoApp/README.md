# 📌 ToDoApp

A **Spring Boot RESTful API** project for managing tasks with user authentication and role-based access control.  
This is my first complete Spring Boot project, covering **CRUD operations, JWT security, password reset with email integration, and role-based authorization**.

---

## ✨ Features
- 🔑 **Authentication & Authorization**
  - JWT-based login & signup
  - Role-based access (`ROLE_USER`, `ROLE_ADMIN`)
- 📝 **Task Management**
  - Create, update, delete, and view tasks
  - Task status: `PENDING`, `IN_PROGRESS`, `COMPLETED`
  - Due date tracking
- 👥 **User Management**
  - Register and manage users
  - Assign roles (`Admin` or `User`)
- 📧 **Password Reset**
  - Email integration for password reset tokens
- 🛡 **Security**
  - Spring Security with JWT filter
  - Custom authentication entry point for handling unauthorized requests

---

## 🛠 Tech Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Spring Data JPA / Hibernate**
- **MySQL / PostgreSQL**
- **Maven**
- **Lombok**
- **Mail Sender (JavaMailSender)**

---

## ⚙️ Setup & Installation
### 1️⃣ Clone Repository
```bash
git clone https://github.com/your-username/todoapp.git
cd todoapp
```

### 2️⃣ Configure Database
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Configure Email (for password reset)
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```
---

## 🚀 Future Improvements
- Add **refresh tokens** for JWT
- Add **task prioritization**
- Add **frontend client** (React/Angular/Vue)

---

## 📝 License
This project is licensed under the MIT License.
