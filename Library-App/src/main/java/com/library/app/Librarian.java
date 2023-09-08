package com.library.app;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Librarian extends User {
    private int librarian_id;

    // Constructors
    public Librarian() {
    }

    // Getters and setters
    public int getId() {
        return librarian_id;
    }
    public void setId(int librarian_id) { this.librarian_id = librarian_id; }

    public boolean checkLibrarian(Connection connection, int id) {
        String checkLibrarianQuery = "SELECT * FROM members WHERE librarian_id = ?";

        try (PreparedStatement checkLibrarianStatement = connection.prepareStatement(checkLibrarianQuery)) {
            checkLibrarianStatement.setInt(1, id);
            ResultSet resultSet = checkLibrarianStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
