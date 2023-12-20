package com22.databasefinalproject.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfig {
    private final static String url = "jdbc:postgresql://localhost:5432/com22";
    private final static String username = "postgres";
    private final static String password = "57206700";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("connected to database ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
