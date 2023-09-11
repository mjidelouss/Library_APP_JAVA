package com.library.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRepository {
    private Connection dbConnection;
    public MemberRepository(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
    public boolean checkMember(int id) {
        String checkMemberQuery = "SELECT * FROM members WHERE member_id = ?";

        try (PreparedStatement checkMemberStatement = dbConnection.prepareStatement(checkMemberQuery)) {
            checkMemberStatement.setInt(1, id);
            ResultSet resultSet = checkMemberStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
