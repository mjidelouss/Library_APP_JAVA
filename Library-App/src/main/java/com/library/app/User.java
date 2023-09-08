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

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
    public User register(Connection connection, String name, String email, String password, String telephone, String adresse) {
        String registerQuery = "INSERT INTO users (name, email, password, telephone, adresse) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement registerStatement = connection.prepareStatement(registerQuery)) {
            registerStatement.setString(1, name);
            registerStatement.setString(2, email);
            registerStatement.setString(3, password);
            registerStatement.setString(4, telephone);
            registerStatement.setString(5, adresse);
            int rowsAffected = registerStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = registerStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    User user = new User(userId, name, email);
                    return user;
                }
            }

            System.out.println("Registration failed.");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public User login(Connection connection, String email, String password) {
        String userLoginQuery = "SELECT user_id, name, email FROM users WHERE email = ? AND password = ?";
        // Check if the user is in the 'librarian' or 'members' table
        String librarianCheckQuery = "SELECT librarian_id FROM librarian WHERE librarian_id = ?";
        String membersCheckQuery = "SELECT member_id FROM members WHERE member_id = ?";

        try (PreparedStatement userLoginStatement = connection.prepareStatement(userLoginQuery);
             PreparedStatement librarianCheckStatement = connection.prepareStatement(librarianCheckQuery);
             PreparedStatement membersCheckStatement = connection.prepareStatement(membersCheckQuery)) {

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
                System.out.println("Invalid email or password.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
