package com.clothingstore.app;

import com.clothingstore.connection.DatabaseConnection;
import com.clothingstore.model.Product;
import com.clothingstore.model.ProductGUI;  // or correct package path

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ClothingStoreApp {
    private static Connection connection;

    public static void main(String[] args) {
        // Establish database connection
        connection = DatabaseConnection.getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Unable to establish a connection to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Create main GUI window
        JFrame frame = new JFrame("Clothing Store Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1));

        // Add buttons for menu options
        JButton addButton = new JButton("1. Add Product");
        JButton updateButton = new JButton("2. Update Product");
        JButton deleteButton = new JButton("3. Delete Product");
        JButton viewButton = new JButton("4. View Products");
        JButton exitButton = new JButton("5. Exit");

        // Add action listeners to buttons
        addButton.addActionListener(e -> ProductGUI.addProductGUI(connection));
        updateButton.addActionListener(e -> ProductGUI.updateProductGUI(connection));
        deleteButton.addActionListener(e -> ProductGUI.deleteProductGUI(connection));
        viewButton.addActionListener(e -> ProductGUI.viewProductsGUI(connection));
        exitButton.addActionListener(e -> System.exit(0));

        // Add buttons to the frame
        frame.add(addButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(viewButton);
        frame.add(exitButton);

        // Make the frame visible
        frame.setVisible(true);
    }
}
