# TodoApp

![TodoApp](./todos.png)

This is my simple web application built with JSP+Servlet, JDBC, SQL, and a few other technologies. It’s designed to showcase how to create, display, and add items to a Todo list using a basic MVC pattern with Servlets and JSP, with data stored in a MySQL database. I built this project with Maven and deployed it as a WAR file to Apache Tomcat.

## What this Does

- **Add Todos:** Users can fill out a form to add new todo items.
- **View Todos:** A list of all todo items is displayed.
- **Data Storage:** Todos are stored in a MySQL database and retrieved using JDBC.
- **Tech Stack:** JSP for the view, Servlets as controllers, JDBC for database operations, and MySQL for data storage.

## Prerequisites

- **Java:** JDK 11 (or higher) installed.
- **Maven:** Required to build the project.
- **Apache Tomcat:** Tomcat 9 (or a compatible version) for deploying the WAR.
- **MySQL:** MySQL 8 (or compatible) running with:
  - A database named `tododb`
  - A user `todo_user` with password `MyStrongPassword` (or update as needed)

## Setup Instructions

### 1. Database Setup

Log in to MySQL with a privileged account (e.g., using the debian-sys-maint account or via sudo) and run the following commands:

```sql
-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS tododb;

-- Use the database
USE tododb;

-- Create the todos table
CREATE TABLE IF NOT EXISTS todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    completed BOOLEAN DEFAULT FALSE
);

-- Drop and recreate the application user (for demo purposes)
DROP USER IF EXISTS 'todo_user'@'localhost';
CREATE USER 'todo_user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'MyStrongPassword';

-- Grant all privileges on the database to todo_user
GRANT ALL PRIVILEGES ON tododb.* TO 'todo_user'@'localhost';
FLUSH PRIVILEGES;
