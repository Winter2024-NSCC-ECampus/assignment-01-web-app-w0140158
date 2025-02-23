package com.example.todo.dao;

import com.example.todo.model.Todo;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TodoDao {

    private static final String DB_PROPERTIES_FILE = "application.properties";
    private String url;
    private String user;
    private String password;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("DEBUG: MySQL JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("DEBUG: MySQL JDBC Driver not found.");
            throw new ExceptionInInitializerError(e);
        }
    }

    public TodoDao() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)) {
            if (input == null) {
                System.err.println("DEBUG: " + DB_PROPERTIES_FILE + " not found in classpath.");
                return;
            }
            Properties props = new Properties();
            props.load(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.username");
            password = props.getProperty("db.password");

            // Debug output (avoid printing password in production)
            System.out.println("DEBUG: Loaded database properties:");
            System.out.println("DEBUG: DB URL: " + url);
            System.out.println("DEBUG: DB User: " + user);
            // System.out.println("DEBUG: DB Password: " + password); // Uncomment for debugging if needed
        } catch (Exception e) {
            System.err.println("DEBUG: Exception while loading database properties: " + e.getMessage());
            throw new RuntimeException("Error loading database properties", e);
        }
    }

    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        String query = "SELECT id, title, description FROM todos";

        System.out.println("DEBUG: Attempting to retrieve todos using URL: " + url);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("DEBUG: Successfully established connection for retrieving todos.");

            while (rs.next()) {
                Todo todo = new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description")
                );
                todos.add(todo);
                System.out.println("DEBUG: Retrieved todo: " + todo);
            }
        } catch (SQLException e) {
            System.err.println("DEBUG: SQLException in getAllTodos: " + e.getMessage());
            throw new RuntimeException("Error retrieving todos", e);
        }
        System.out.println("DEBUG: Total todos retrieved: " + todos.size());
        return todos;
    }

    public void addTodo(Todo todo) {
        String query = "INSERT INTO todos (title, description) VALUES (?, ?)";
        System.out.println("DEBUG: Adding todo with title: " + todo.getTitle());

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            System.out.println("DEBUG: Successfully established connection for inserting todo.");

            ps.setString(1, todo.getTitle());
            ps.setString(2, todo.getDescription());
            int rowsAffected = ps.executeUpdate();
            System.out.println("DEBUG: Inserted todo. Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            System.err.println("DEBUG: SQLException in addTodo: " + e.getMessage());
            throw new RuntimeException("Error inserting todo", e);
        }
    }
}
