package com.library.app;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection dbConnection = DbConnection.connect();

        if (dbConnection != null) {
            Book bookManager = new Book();
            bookManager.displayBooks(dbConnection, "Available");

            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
