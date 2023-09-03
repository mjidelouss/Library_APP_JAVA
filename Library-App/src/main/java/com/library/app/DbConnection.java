package com.library.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection connect(){
        String url = "jdbc:mysql://localhost:3306/mylibrary";
        String user = "root";
        String password = "";
        Connection connection;
        try {
            // Create a connection to the database
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
