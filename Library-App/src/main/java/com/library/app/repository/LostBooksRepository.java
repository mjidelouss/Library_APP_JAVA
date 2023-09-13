package com.library.app.repository;

import com.library.app.domain.LostBook;

import java.sql.*;
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

    public void insertLostBooks() {
        try {
            String insertQuery = "INSERT INTO lostBooks (book_isbn, book_title, book_author, book_category, book_year) " +
                    "SELECT book_isbn, book_title, book_author, book_category, book_year " +
                    "FROM borrowedBooks WHERE due_date < CURDATE() " +
                    "AND NOT EXISTS (SELECT 1 FROM lostBooks WHERE lostBooks.book_isbn = borrowedBooks.book_isbn)";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
