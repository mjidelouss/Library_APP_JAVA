package com.library.app.domain;

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
}
