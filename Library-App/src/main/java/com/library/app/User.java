package com.library.app;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String telephone;
    private String address;

    // Constructors

    public User() {
    }

    public User(String name, String email, String password, String telephone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public boolean register(Connection connection, String name, String email, String password, String telephone, String address) {

        String insertSql = "INSERT INTO users (name, email, password, telephone, address) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, telephone);
            preparedStatement.setString(5, address);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User registered successfully.");
                return true;
            } else {
                System.out.println("Failed to register the user.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean login(Connection connection, String email, String password) {
        String userLoginQuery = "SELECT id FROM users WHERE email = ? AND password = ?";
        // Check if the user is in the 'librarian' or 'members' table
        String librarianCheckQuery = "SELECT user_id FROM librarian WHERE user_id = ?";
        String membersCheckQuery = "SELECT user_id FROM members WHERE user_id = ?";

        try (PreparedStatement userLoginStatement = connection.prepareStatement(userLoginQuery);
             PreparedStatement librarianCheckStatement = connection.prepareStatement(librarianCheckQuery);
             PreparedStatement membersCheckStatement = connection.prepareStatement(membersCheckQuery)) {

            // Check if the email and password exist in the 'users' table
            userLoginStatement.setString(1, email);
            userLoginStatement.setString(2, password);
            ResultSet resultSet = userLoginStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("id");

                // Check if the user is in the 'librarian' table
                librarianCheckStatement.setInt(1, userId);
                ResultSet librarianResult = librarianCheckStatement.executeQuery();

                // Check if the user is in the 'members' table
                membersCheckStatement.setInt(1, userId);
                ResultSet membersResult = membersCheckStatement.executeQuery();

                if (librarianResult.next()) {
                    System.out.println("Librarian logged in successfully.");
                    return true;
                } else if (membersResult.next()) {
                    System.out.println("Member logged in successfully.");
                    return true;
                } else {
                    System.out.println("User is not a librarian or member.");
                    return false;
                }
            } else {
                System.out.println("Invalid email or password.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
