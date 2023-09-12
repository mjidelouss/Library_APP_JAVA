package com.library.app.repository;

import com.library.app.domain.Book;
import com.library.app.domain.enums.Colors;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private Connection dbConnection;
    public BookRepository(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Methods
    public Book addBook(Book book) {
        String isbn = book.getIsbn();
        Book addedBook = null;
        String checkExistenceQuery = "SELECT id, quantity FROM books WHERE isbn = ?";

        try (PreparedStatement checkExistenceStatement = dbConnection.prepareStatement(checkExistenceQuery)) {
            checkExistenceStatement.setString(1, isbn);
            ResultSet resultSet = checkExistenceStatement.executeQuery();

            if (resultSet.next()) {
                // If a book with the same ISBN exists, update its quantity
                int existingBookId = resultSet.getInt("id");
                int existingQuantity = resultSet.getInt("quantity");
                int newQuantity = existingQuantity + book.getQuantity();

                String updateQuantityQuery = "UPDATE books SET quantity = ? WHERE id = ?";
                try (PreparedStatement updateQuantityStatement = dbConnection.prepareStatement(updateQuantityQuery)) {
                    updateQuantityStatement.setInt(1, newQuantity);
                    updateQuantityStatement.setInt(2, existingBookId);
                    updateQuantityStatement.executeUpdate();
                    System.out.println(Colors.GREEN.getColor() +"\n\nBook with ISBN " + isbn + " already exists. Quantity updated.");
                }
            } else {
                // Insert a new book if ISBN doesn't exist
                String insertQuery = "INSERT INTO books (title, author, isbn, quantity, category, year) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = dbConnection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, book.getTitle());
                    preparedStatement.setString(2, book.getAuthor());
                    preparedStatement.setString(3, isbn);
                    preparedStatement.setInt(4, book.getQuantity());
                    preparedStatement.setString(5, book.getCategory());
                    preparedStatement.setInt(6, book.getYear());

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        addedBook = new Book(book.getTitle(), book.getAuthor(), isbn, book.getQuantity(), book.getCategory(), book.getYear());
                        System.out.println(Colors.GREEN.getColor() +"\n\nNew book added successfully.");
                    } else {
                        System.out.println(Colors.RED.getColor() +"\n\nFailed to add the book.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addedBook;
    }
    public Book updateBook(String oldIsbn, Book book) {
        String selectSql = "SELECT * FROM books WHERE isbn = ?";
        Book updatedBook = null;

        try (PreparedStatement selectStatement = dbConnection.prepareStatement(selectSql)) {
            selectStatement.setString(1, oldIsbn);

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String existingTitle = resultSet.getString("title");
                String existingAuthor = resultSet.getString("author");
                int existingQuantity = resultSet.getInt("quantity");
                String existingCategory = resultSet.getString("category");
                int existingYear = resultSet.getInt("year");

                if (book.getTitle().isEmpty()) book.setTitle(existingTitle);
                if (book.getAuthor().isEmpty()) book.setAuthor(existingAuthor);
                if (book.getQuantity() <= 0) book.setQuantity(existingQuantity);
                if (book.getCategory().isEmpty()) book.setCategory(existingCategory);
                if (book.getYear() <= 0) book.setYear(existingYear);

                String updateSql = "UPDATE books SET title = ?, author = ?, isbn = ?, quantity = ?, category = ?, year = ? WHERE isbn = ?";

                try (PreparedStatement updateStatement = dbConnection.prepareStatement(updateSql)) {
                    updateStatement.setString(1, book.getTitle());
                    updateStatement.setString(2, book.getAuthor());
                    updateStatement.setString(3, book.getIsbn());
                    updateStatement.setInt(4, book.getQuantity());
                    updateStatement.setString(5, book.getCategory());
                    updateStatement.setInt(6, book.getYear());
                    updateStatement.setString(7, book.getIsbn());

                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        updatedBook = new Book(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getQuantity(), book.getCategory(), book.getYear());
                        System.out.println(Colors.GREEN.getColor() +"\n\nBook with ISBN " + book.getIsbn() + " updated successfully.");
                    } else {
                        System.out.println(Colors.RED.getColor() +"\n\nNo book found with ISBN " + book.getIsbn() + ". Update failed.");
                    }
                }
            } else {
                System.out.println(Colors.RED.getColor() +"\n\nNo book found with ISBN " + book.getIsbn() + ". Update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedBook;
    }
    public boolean deleteBook(String isbn, int quantity) {
        String selectSql = "SELECT id, quantity FROM books WHERE isbn = ?";
        String deleteSql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement selectStatement = dbConnection.prepareStatement(selectSql);
             PreparedStatement deleteStatement = dbConnection.prepareStatement(deleteSql)) {

            // Retrieve book
            selectStatement.setString(1, isbn);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                int currentQuantity = resultSet.getInt("quantity");

                // Check if the book has enough copies to delete
                if (currentQuantity >= quantity) {
                    int newQuantity = currentQuantity - quantity;

                    if (newQuantity > 0) {
                        // Update the quantity in database
                        String updateQuantitySql = "UPDATE books SET quantity = ? WHERE id = ?";
                        try (PreparedStatement updateStatement = dbConnection.prepareStatement(updateQuantitySql)) {
                            updateStatement.setInt(1, newQuantity);
                            updateStatement.setInt(2, bookId);
                            updateStatement.executeUpdate();
                        }
                    } else {
                        // If the quantity becomes zero, delete the book from the database
                        deleteStatement.setInt(1, bookId);
                        deleteStatement.executeUpdate();
                    }

                    System.out.println(Colors.GREEN.getColor() +"\n\nSuccessfully deleted " + quantity + " copies of the book with ISBN " + isbn + ".");
                    return true;
                } else {
                    System.out.println(Colors.RED.getColor() +"\n\nInsufficient quantity. Unable to delete " + quantity + " copies of the book with ISBN " + isbn + ".");
                    return false;
                }
            } else {
                System.out.println(Colors.RED.getColor() +"\n\nNo book found with ISBN " + isbn + ".");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to display books
    public List<Book> displayBooks() {
        String query = "SELECT * FROM books";
        List<Book> bookList = new ArrayList<>();

        try (Statement statement = dbConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                int quantity = resultSet.getInt("quantity");
                String category = resultSet.getString("category");
                int year = resultSet.getInt("year");

                Book book = new Book(title, author, isbn, quantity, category, year);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
    public List<Book> searchBook(String searchTerm) {
        String query = "SELECT DISTINCT * FROM books WHERE title LIKE ? OR author LIKE ?";
        List<Book> searchResults = new ArrayList<>();

        try {
            PreparedStatement statement = dbConnection.prepareStatement(query);
            String searchPattern = "%" + searchTerm + "%";
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setCategory(resultSet.getString("category"));
                book.setYear(resultSet.getInt("year"));
                searchResults.add(book);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    public int[] bookStatistics() {
        int[] bookCounts = new int[3];

        try {
            // Query to get the count of available books
            String availableBooksQuery = "SELECT COUNT(*) AS available_books FROM books";
            PreparedStatement availableBooksStatement = dbConnection.prepareStatement(availableBooksQuery);
            ResultSet availableBooksResult = availableBooksStatement.executeQuery();

            if (availableBooksResult.next()) {
                bookCounts[0] = availableBooksResult.getInt("available_books");
            }

            // Query to get the count of borrowed books
            String borrowedBooksQuery = "SELECT COUNT(*) AS borrowed_books FROM borrowedBooks";
            PreparedStatement borrowedBooksStatement = dbConnection.prepareStatement(borrowedBooksQuery);
            ResultSet borrowedBooksResult = borrowedBooksStatement.executeQuery();

            if (borrowedBooksResult.next()) {
                bookCounts[1] = borrowedBooksResult.getInt("borrowed_books");
            }

            // Query to get the count of lost books
            String lostBooksQuery = "SELECT COUNT(*) AS lost_books FROM lostBooks";
            PreparedStatement lostBooksStatement = dbConnection.prepareStatement(lostBooksQuery);
            ResultSet lostBooksResult = lostBooksStatement.executeQuery();

            if (lostBooksResult.next()) {
                bookCounts[2] = lostBooksResult.getInt("lost_books");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookCounts;
    }

    public int getBookQuantity(String isbn) {
        String sqlQuery = "SELECT quantity FROM books WHERE isbn = ?";
        int bookQuantity = 0;

        try (PreparedStatement selectStatement = dbConnection.prepareStatement(sqlQuery)) {
            selectStatement.setString(1, isbn);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                bookQuantity = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookQuantity;
    }
}
