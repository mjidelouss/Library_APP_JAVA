package com.library.app;
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

    public boolean checkMember(Connection connection, int id) {
        String checkMemberQuery = "SELECT * FROM members WHERE member_id = ?";

        try (PreparedStatement checkMemberStatement = connection.prepareStatement(checkMemberQuery)) {
            checkMemberStatement.setInt(1, id);
            ResultSet resultSet = checkMemberStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
