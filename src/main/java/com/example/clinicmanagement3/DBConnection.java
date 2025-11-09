package com.example.clinicmanagement3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =
            "jdbc:mysql://localhost:3306/clinicdb?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "@Shane";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected successfully!");
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Database connection error:");
            System.err.println("   SQLState: " + e.getSQLState());
            System.err.println("   ErrorCode: " + e.getErrorCode());
            System.err.println("   Message: " + e.getMessage());
            return null;
        }
    }
}
