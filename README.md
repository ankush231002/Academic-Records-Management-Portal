
# 🎓 Academic Records Management Portal

A production-ready RESTful API built with **Spring Boot** and **MySQL** for managing student academic records — including profile images, documents, and bank details — with smart partial update logic and custom validations.

---

![Screenshot_3](https://github.com/user-attachments/assets/4badb208-a822-4152-b706-94ad336786f6)


![Screenshot_4](https://github.com/user-attachments/assets/6d4a6eea-e434-487e-8b94-b0ddf42afa37)




## ✨ Features

- 📋 **Full CRUD** — Create, Read, Update, Delete student records
- 🖼️ **Profile Image Upload** — Upload, replace, or remove student profile photos
- 📄 **Document Management** — Upload multiple documents per student with targeted replacement by path
- 🏦 **Multiple Bank Accounts** — Each student can have multiple bank account entries
- 🔒 **Smart Update Logic** — Preserves existing data when fields are not updated (no accidental deletes)
- ⚠️ **Custom Validations** — Count mismatch, duplicate paths, invalid references

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| File Storage | Local File System |
| Build Tool | Maven |

---

## 📁 Project Structure

```
src/
└── main/java/com/example/ankush/
    ├── controller/        # REST endpoints
    ├── service/           # Business logic
    ├── repository/        # Database layer
    ├── entity/            # JPA entities (User, Bank, StudentDocuments)
    ├── dto/               # Data Transfer Objects
    └── config/            # Web configuration
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Maven

### Setup

**1. Clone the repository**
```bash
git clone https://github.com/ankush231002/Academic-Records-Management-Portal.git
cd Academic-Records-Management-Portal
```

**2. Configure the database**

Create a MySQL database:
```sql
CREATE DATABASE my_project_db;
```

Copy the example properties file and fill in your credentials:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edit `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/my_project_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
server.error.include-stacktrace=never
```

**3. Run the application**
```bash
mvnw.cmd clean spring-boot:run       # Windows
./mvnw clean spring-boot:run         # Mac/Linux
```

The server starts at `http://localhost:8080`

---

## 📡 API Endpoints

### Student

| Method | Endpoint | Description |
|---|---|---|
| GET | `/user/all` | Get all students |
| GET | `/user/{studentId}` | Get student by ID |
| POST | `/user/create` | Create new student |
| PUT | `/user/{studentId}` | Update student |
| DELETE | `/user/{studentId}` | Delete student |

### Health Check

| Method | Endpoint | Description |
|---|---|---|
| GET | `/health` | Check server status |

---

## 📬 Request Examples (Postman)

### Create Student — `POST /user/create`

Use `multipart/form-data` with:

| Key | Type | Description |
|---|---|---|
| data | Text (JSON) | Student details |
| image | File | Profile photo (optional) |
| documents | File | Documents (optional, multiple) |

**`data` JSON example:**
```json
{
  "studentName": "Rahul",
  "studentClass": "10-A",
  "fatherName": "Mohan",
  "dob": "2010-01-15",
  "gender": "Male",
  "nationality": "India",
  "phoneNo": "98765",
  "address": "Delhi",
  "aadharNo": "12345",
  "bankDetails": [
    {
      "bankName": "SBI",
      "branchName": "Main",
      "accountNo": "11111",
      "ifscCode": "SBI01"
    }
  ]
}
```

### Update Specific Documents — `PUT /user/{studentId}`

To replace specific documents, include `deleteDocumentPaths` in your JSON:

```json
{
  "studentName": "Rahul",
  "deleteDocumentPaths": [
    "/uploads/documents/Stu0001/Stu0001_1.jpg"
  ]
}
```

And attach the new file in the `documents` field. The number of paths must match the number of new files.

---

## ⚙️ Update Logic

| Scenario | Image | Documents |
|---|---|---|
| Field not sent | ✅ Preserved | ✅ Preserved |
| Field sent, empty | ❌ Deleted | ❌ Deleted |
| Field sent, new file | 🔄 Replaced | 🔄 Replaced |
| `deleteDocumentPaths` used | — | 🎯 Targeted replace |

---

## 👨‍💻 Author

**Ankush**  
[GitHub](https://github.com/ankush231002)
