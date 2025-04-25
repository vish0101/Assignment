# Assignment
1-> Features
CRUD operations for Users and Tasks
Prevents duplicate tasks for the same user (same title and end time)
RESTful architecture using Spring Boot
MySQL integration with Spring Data JPA

2-> Prerequisites
Internet required for Downloading Dependencies from Remote Repositories
Before running the application, ensure you have the following installed:
Java 17 or higher
MySQL Server
Postman or any REST API testing tool (optional)

3-> Update application.properties 
configure your database from application.properties(url , username , password)
 
4->  Run the Application
    Once started, the API will be available at:
    http://localhost:8080

5->ENDPOINT
   POST /api/tasks: Create a new task.
   GET /api/tasks: Retrieve all tasks.
   GET /api/tasks/{id}: Retrieve a task by its ID.
   PUT /api/tasks/{id}: Update a task.
   DELETE /api/tasks/{id}: Delete a task by its ID.
   GET /api/deletedTask/all : Show all deleted task













