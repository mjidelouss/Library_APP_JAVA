package com.library.app.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Member extends User {
    private int member_id;

    // Constructors
    public Member() {
    }

    // Getters and setters
    public int getId() {
        return member_id;
    }

    public void setId(int member_id) { this.member_id = member_id; }

}
