package com.library.app;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    private String category;
    private int year;
    private String status;

    // Constructor

    public Book() {

    }
    public Book(String title, String author, String isbn, int quantity, String category, int year, String status) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
        this.category = category;
        this.year = year;
        this.status = status;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to add a book
    public void addBook(Connection connection) {
        String sqlQuery = "INSERT INTO books (title, author, isbn, quantity, category, year, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, this.title);
            preparedStatement.setString(2, this.author);
            preparedStatement.setString(3, this.isbn);
            preparedStatement.setInt(4, this.quantity);
            preparedStatement.setString(5, this.category);
            preparedStatement.setInt(6, this.year);
            preparedStatement.setString(7, this.status);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book added successfully.");
            } else {
                System.out.println("Failed to add the book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to display books
    public void displayBooks(Connection connection) {
        String query = "SELECT * FROM books";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                int quantity = resultSet.getInt("quantity");
                String category = resultSet.getString("category");
                int year = resultSet.getInt("year");
                String status = resultSet.getString("status");

                System.out.println("ID: " + id);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("ISBN: " + isbn);
                System.out.println("Quantity: " + quantity);
                System.out.println("Category: " + category);
                System.out.println("Year: " + year);
                System.out.println("Status: " + status);
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}