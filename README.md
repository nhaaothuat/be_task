# Task Management System - Spring Boot Backend

## 📝 Overview
A RESTful API for task management with:
- User authentication (signup/login)
- Role-based access (Admin/Employee) 
- CRUD operations for tasks
- Task status updates

## 🛠 Tech Stack
- **Backend**: Spring Boot 3.x
- **Database**: MySQL
- **Authentication**: JWT
- **Architecture**: MVC pattern

## 📂 Project Structure
src/
├── main/
│ ├── java/
│ │ ├── configs/ # App configurations
│ │ ├── controller/ # API controllers
│ │ ├── dto/ # Data Transfer Objects
│ │ ├── entities/ # JPA entities
│ │ ├── enums/ # Enumerations
│ │ ├── repositories/ # Spring Data JPA
│ │ ├── services/ # Business logic
│ │ └── utils/ # Utilities
│ └── resources/ # application.properties
└── test/ # Unit tests


## 🌐 API Endpoints

### 🔐 Authentication
| Method | Endpoint              | Description          |
|--------|-----------------------|----------------------|
| POST   | `/api/auth/signup`    | Register new user    |
| POST   | `/api/auth/signin`    | User login          |

### 👨‍💼 Employee Routes
| Method | Endpoint                          | Description                |
|--------|-----------------------------------|----------------------------|
| GET    | `/api/employee/tasks`             | Get assigned tasks        |
| PUT    | `/api/employee/task/{id}/{status}`| Update task status        |

### 👨‍💻 Admin Routes
#### Task Management
| Method | Endpoint                  | Description            |
|--------|---------------------------|------------------------|
| GET    | `/api/admin/task`          | List all tasks         |
| GET    | `/api/admin/task/{id}`     | Get task by ID         |
| POST   | `/api/admin/task`          | Create new task        |
| PUT    | `/api/admin/task/{id}`     | Update task            |
| DELETE | `/api/admin/task/{id}`     | Delete task            |
| GET    | `/api/admin/task/search/{title}` | Search tasks        |

#### User Management
| Method | Endpoint            | Description        |
|--------|---------------------|--------------------|
| GET    | `/api/admin/users`  | List all users     |

## 🚀 Installation
1. Clone the repo:
   ```bash
   git clone https://github.com/nhaaothuat/be_task.git

2. Configure database in application.properties

3. Run:
mvn spring-boot:run
