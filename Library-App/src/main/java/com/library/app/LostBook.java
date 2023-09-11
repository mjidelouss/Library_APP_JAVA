package com.library.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LostBook {
    private int id;
    private String bookIsbn;
    private String bookTitle;
    private String book_author;
    private String book_category;
    private int book_year;

    public LostBook() {

    }
    public LostBook(int id, String bookIsbn, String bookTitle, String book_author, String book_category, int book_year) {
        this.id = id;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.book_author = book_author;
        this.book_category = book_category;
        this.book_year = book_year;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBookAuthor() {
        return book_author;
    }

    public void setBookAuthor(String author) {
        this.book_author = author;
    }
    public String getBookCategory() {
        return book_category;
    }

    public void setBookCategory(String category) {
        this.book_category = category;
    }

    public int getBookYear() {
        return book_year;
    }

    public void setBookYear(int year) {
        this.book_year = year;
    }

    public List<LostBook> displayLostBooks(Connection connection) {
        String query = "SELECT * FROM lostBooks";
        List<LostBook> lostBookList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
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
