Task Tracker App
================

A productivity-focused web application that helps users organize their daily tasks, set priorities, and track progress efficiently.
Built with Spring Boot for the backend, PostgreSQL for data persistence, and containerized using Docker Compose.

------------------------------------------------------------
🚀 Features
------------------------------------------------------------
- Task Lists Management
  - Create, update, and delete task lists with titles and optional descriptions.
  - Organize tasks into separate lists for better project and personal management.

- Task Management
  - Add new tasks with title, description, due date, and priority.
  - Update or delete tasks as priorities change.
  - Mark tasks as complete to track progress.

- Progress Tracking
  - View completion percentage of tasks in each list for motivation and productivity insights.

------------------------------------------------------------
🛠 Tech Stack
------------------------------------------------------------
- Backend Framework: Spring Boot
- Database: PostgreSQL (via Docker Compose)
- API Design: RESTful APIs for client-server communication
- ORM: JPA / Hibernate
- Build Tool: Maven
- Containerization: Docker & Docker Compose

------------------------------------------------------------
📂 Project Structure
------------------------------------------------------------
TaskTracker/
 ├── src/main/java/...   # Spring Boot application code
 ├── src/main/resources/ # Configuration files
 ├── pom.xml              # Maven dependencies
 ├── docker-compose.yml   # Docker services configuration
 └── README.txt           # Project documentation

------------------------------------------------------------
⚙️ Installation & Setup
------------------------------------------------------------
1. Clone the Repository
   git clone https://github.com/SujalRatho/TaskTracker.git
   cd TaskTracker

2. Start Services with Docker Compose
   docker-compose up -d

3. Run the Application
   mvn spring-boot:run

4. Access the APIs
   Default URL: http://localhost:8080

------------------------------------------------------------
📌 API Endpoints (Examples)
------------------------------------------------------------
Method | Endpoint                | Description
-------|-------------------------|--------------------------------
GET    | /tasklists              | Get all task lists
POST   | /tasklists              | Create a new task list
PUT    | /tasklists/{id}         | Update a task list
DELETE | /tasklists/{id}         | Delete a task list
POST   | /tasklists/{id}/tasks   | Add a new task to a task list
PUT    | /tasks/{id}             | Update a task
DELETE | /tasks/{id}             | Delete a task
PATCH  | /tasks/{id}/complete    | Mark a task as complete

