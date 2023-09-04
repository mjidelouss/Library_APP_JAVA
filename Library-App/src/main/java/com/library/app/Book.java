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

    // Constructors

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
        String isbn = this.isbn;

        // Check if a book with the same ISBN already exists
        String checkExistenceQuery = "SELECT id, quantity FROM books WHERE isbn = ?";

        try (PreparedStatement checkExistenceStatement = connection.prepareStatement(checkExistenceQuery)) {
            checkExistenceStatement.setString(1, isbn);
            ResultSet resultSet = checkExistenceStatement.executeQuery();

            if (resultSet.next()) {
                // If a book with the same ISBN exists update its quantity
                int existingBookId = resultSet.getInt("id");
                int existingQuantity = resultSet.getInt("quantity");
                int newQuantity = existingQuantity + this.quantity;

                String updateQuantityQuery = "UPDATE books SET quantity = ? WHERE id = ?";
                try (PreparedStatement updateQuantityStatement = connection.prepareStatement(updateQuantityQuery)) {
                    updateQuantityStatement.setInt(1, newQuantity);
                    updateQuantityStatement.setInt(2, existingBookId);
                    updateQuantityStatement.executeUpdate();
                    System.out.println("Book with ISBN " + isbn + " already exists. Quantity updated.");
                }
            } else {
                // Insert a new book if ISBN doesn't exist
                String insertQuery = "INSERT INTO books (title, author, isbn, quantity, category, year, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, this.title);
                    preparedStatement.setString(2, this.author);
                    preparedStatement.setString(3, isbn);
                    preparedStatement.setInt(4, this.quantity);
                    preparedStatement.setString(5, this.category);
                    preparedStatement.setInt(6, this.year);
                    preparedStatement.setString(7, this.status);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("New book added successfully.");
                    } else {
                        System.out.println("Failed to add the book.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateBook(Connection connection, String newIsbn, String newTitle, String newAuthor, int newQuantity, String newCategory, int newYear, String newStatus) {
        String selectSql = "SELECT * FROM books WHERE isbn = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setString(1, newIsbn);

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String existingTitle = resultSet.getString("title");
                String existingAuthor = resultSet.getString("author");
                int existingQuantity = resultSet.getInt("quantity");
                String existingCategory = resultSet.getString("category");
                int existingYear = resultSet.getInt("year");
                String existingStatus = resultSet.getString("status");

                if (newTitle.isEmpty()) newTitle = existingTitle;
                if (newAuthor.isEmpty()) newAuthor = existingAuthor;
                if (newQuantity <= 0) newQuantity = existingQuantity;
                if (newCategory.isEmpty()) newCategory = existingCategory;
                if (newYear <= 0) newYear = existingYear;
                if (newStatus.isEmpty()) newStatus = existingStatus;

                String updateSql = "UPDATE books SET title = ?, author = ?, isbn = ?, quantity = ?, category = ?, year = ?, status = ? WHERE isbn = ?";

                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setString(1, newTitle);
                    updateStatement.setString(2, newAuthor);
                    updateStatement.setString(3, newIsbn);
                    updateStatement.setInt(4, newQuantity);
                    updateStatement.setString(5, newCategory);
                    updateStatement.setInt(6, newYear);
                    updateStatement.setString(7, newStatus);
                    updateStatement.setString(8, newIsbn);

                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Book with ISBN " + newIsbn + " updated successfully.");
                    } else {
                        System.out.println("No book found with ISBN " + newIsbn + ". Update failed.");
                    }
                }
            } else {
                System.out.println("No book found with ISBN " + newIsbn + ". Update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean deleteBook(Connection connection, String isbn) {
        String selectSql = "SELECT id, quantity FROM books WHERE isbn = ?";
        String deleteSql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql)) {

            // Retrieve book
            selectStatement.setString(1, isbn);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                int quantity = resultSet.getInt("quantity");

                // Check if the book still has copies
                if (quantity > 1) {
                    quantity--;
                    // Update the quantity in database
                    String updateQuantitySql = "UPDATE books SET quantity = ? WHERE id = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuantitySql)) {
                        updateStatement.setInt(1, quantity);
                        updateStatement.setInt(2, bookId);
                        updateStatement.executeUpdate();
                    }
                } else {
                    // If there's only one copy delete the book from the database
                    deleteStatement.setInt(1, bookId);
                    deleteStatement.executeUpdate();
                }

                System.out.println("Book with ISBN " + isbn + " deleted successfully.");
                return true;
            } else {
                System.out.println("No book found with ISBN " + isbn + ".");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to display books
    public void displayBooks(Connection connection, String stat) {
        String query;
        if ("available".equalsIgnoreCase(stat)) {
            query = "SELECT * FROM books WHERE status = 'Available'";
        } else if ("checked out".equalsIgnoreCase(status)) {
            query = "SELECT * FROM books WHERE status = 'Checked Out'";
        } else {
            query = "SELECT * FROM books";
        }

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("List of Books:");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-30s %-30s %-15s %-10s %-15s %-10s %-15s%n",
                    "ID", "Title", "Author", "ISBN", "Quantity", "Category", "Year", "Status");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                int quantity = resultSet.getInt("quantity");
                String category = resultSet.getString("category");
                int year = resultSet.getInt("year");
                String status = resultSet.getString("status");

                System.out.printf("%-5d %-30s %-30s %-15s %-10d %-15s %-10d %-15s%n",
                        id, title, author, isbn, quantity, category, year, status);
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}