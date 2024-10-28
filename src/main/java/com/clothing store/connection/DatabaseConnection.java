package com.clothingstore.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/clothing_store";
    private static final String USER = "root";
    private static final String PASSWORD = "Root@123";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Failed to establish connection to the database.");
            e.printStackTrace();
            return null;
        }
    }
}
