package com.contactcatalog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/contact_catalog";
    private static final String USER = "root";
    private static final String PASSWORD = "FAla12/?";
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Configure Logger to print to console
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            LOGGER.addHandler(consoleHandler);
            LOGGER.setLevel(Level.ALL);

            LOGGER.info("MySQL Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "MySQL Driver not found", e);
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.info("Database connected successfully.");
            return connection;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database connection error", e);
            throw new RuntimeException("Database connection error", e);
        }
    }
}
