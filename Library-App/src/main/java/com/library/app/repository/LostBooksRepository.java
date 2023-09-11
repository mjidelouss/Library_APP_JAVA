package com.library.app.repository;

import com.library.app.domain.LostBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LostBooksRepository {
    private Connection dbConnection;
    public LostBooksRepository(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
    public List<LostBook> displayLostBooks() {
        String query = "SELECT * FROM lostBooks";
        List<LostBook> lostBookList = new ArrayList<>();

        try (Statement statement = dbConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("book_title");
                String author = resultSet.getString("book_author");
                String isbn = resultSet.getString("book_isbn");
                String category = resultSet.getString("book_category");
                int year = resultSet.getInt("book_year");

                LostBook lostBook = new LostBook(id, isbn, title, author, category, year);
                lostBookList.add(lostBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lostBookList;
    }
}
