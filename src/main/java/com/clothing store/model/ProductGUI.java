package com.clothingstore.model;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductGUI {

    // GUI to Add a New Product
    public static void addProductGUI(Connection connection) {
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        Object[] inputFields = {
                "Product Name:", nameField,
                "Price:", priceField,
                "Quantity:", quantityField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Add Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                Product.addProduct(connection, name, price, quantity);
                JOptionPane.showMessageDialog(null, "Product added successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // GUI to Update an Existing Product
    public static void updateProductGUI(Connection connection) {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        Object[] inputFields = {
                "Product ID:", idField,
                "New Product Name:", nameField,
                "New Price:", priceField,
                "New Quantity:", quantityField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Update Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                Product.updateProduct(connection, id, name, price, quantity);
                JOptionPane.showMessageDialog(null, "Product updated successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // GUI to Delete a Product
    public static void deleteProductGUI(Connection connection) {
        JTextField idField = new JTextField();
        Object[] inputFields = {"Product ID:", idField};

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Delete Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                Product.deleteProduct(connection, id);
                JOptionPane.showMessageDialog(null, "Product deleted successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // GUI to View All Products
    public static void viewProductsGUI(Connection connection) {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        StringBuilder productData = new StringBuilder();
        try {
            List<Product> products = Product.getProducts(connection);
            for (Product product : products) {
                productData.append("ID: ").append(product.getId()).append("\n");
                productData.append("Name: ").append(product.getName()).append("\n");
                productData.append("Price: ").append(product.getPrice()).append("\n");
                productData.append("Quantity: ").append(product.getQuantity()).append("\n");
                productData.append("-----------------------------\n");
            }

            textArea.setText(productData.toString());
            JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Product List", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve products.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
