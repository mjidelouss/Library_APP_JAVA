package com.library.app.repository;

import com.library.app.domain.User;
import com.library.app.domain.enums.Colors;

import java.sql.*;

public class UserRepository {
    private Connection dbConnection;
    public UserRepository(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
    public User register(String name, String email, String password, String telephone, String adresse) {
        String registerQuery = "INSERT INTO users (name, email, password, telephone, address) VALUES (?, ?, ?, ?, ?)";
        String selectQuery = "SELECT LAST_INSERT_ID() AS user_id";

        try (PreparedStatement registerStatement = dbConnection.prepareStatement(registerQuery);
             Statement selectStatement = dbConnection.createStatement()) {
            registerStatement.setString(1, name);
            registerStatement.setString(2, email);
            registerStatement.setString(3, password);
            registerStatement.setString(4, telephone);
            registerStatement.setString(5, adresse);
            int rowsAffected = registerStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = selectStatement.executeQuery(selectQuery);
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    User user = new User(userId, name, email);
                    return user;
                }
            }
            System.out.println(Colors.RED.getColor() +"\n\nRegistration failed.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User login(String email, String password) {
        String userLoginQuery = "SELECT user_id, name, email FROM users WHERE email = ? AND password = ?";
        String librarianCheckQuery = "SELECT librarian_id FROM librarian WHERE librarian_id = ?";
        String membersCheckQuery = "SELECT member_id FROM members WHERE member_id = ?";

        try (PreparedStatement userLoginStatement = dbConnection.prepareStatement(userLoginQuery);
             PreparedStatement librarianCheckStatement = dbConnection.prepareStatement(librarianCheckQuery);
             PreparedStatement membersCheckStatement = dbConnection.prepareStatement(membersCheckQuery)) {

            // Check if the email and password exist in the 'users' table
            userLoginStatement.setString(1, email);
            userLoginStatement.setString(2, password);
            ResultSet resultSet = userLoginStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("name");
                String userEmail = resultSet.getString("email");
                User user = new User(userId, userName, userEmail);
                return user;
            } else {
                System.out.println(Colors.RED.getColor() +"\n\nInvalid email or password.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
