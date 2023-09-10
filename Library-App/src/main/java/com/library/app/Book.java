package com.library.app;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
    public Book addBook(Connection connection) {
        String isbn = this.isbn;
        Book addedBook = null;
        String checkExistenceQuery = "SELECT id, quantity FROM books WHERE isbn = ?";

        try (PreparedStatement checkExistenceStatement = connection.prepareStatement(checkExistenceQuery)) {
            checkExistenceStatement.setString(1, isbn);
            ResultSet resultSet = checkExistenceStatement.executeQuery();

            if (resultSet.next()) {
                // If a book with the same ISBN exists, update its quantity
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
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, this.title);
                    preparedStatement.setString(2, this.author);
                    preparedStatement.setString(3, isbn);
                    preparedStatement.setInt(4, this.quantity);
                    preparedStatement.setString(5, this.category);
                    preparedStatement.setInt(6, this.year);
                    preparedStatement.setString(7, this.status);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                            addedBook = new Book(this.title, this.author, isbn, this.quantity, this.category, this.year, this.status);
                            System.out.println("New book added successfully.");
                    } else {
                        System.out.println("Failed to add the book.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addedBook;
    }
    public Book updateBook(Connection connection, String newIsbn, String oldIsbn, String newTitle, String newAuthor, int newQuantity, String newCategory, int newYear, String newStatus) {
        String selectSql = "SELECT * FROM books WHERE isbn = ?";
        Book updatedBook = null;

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
            selectStatement.setString(1, oldIsbn);

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
                        updatedBook = new Book(newTitle, newAuthor, newIsbn, newQuantity, newCategory, newYear, newStatus);
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
        return updatedBook;
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
    public List<Book> displayBooks(Connection connection, String stat) {
        String query;
        if ("available".equalsIgnoreCase(stat)) {
            query = "SELECT * FROM books WHERE status = 'Available'";
        } else if ("checked out".equalsIgnoreCase(stat)) {
            query = "SELECT * FROM books WHERE status = 'Checked Out'";
        } else {
            query = "SELECT * FROM books";
        }

        List<Book> bookList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                int quantity = resultSet.getInt("quantity");
                String category = resultSet.getString("category");
                int year = resultSet.getInt("year");
                String status = resultSet.getString("status");

                Book book = new Book(title, author, isbn, quantity, category, year, status);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    public List<Book> searchBook(Connection connection, String searchTerm) {
        String query = "SELECT DISTINCT * FROM books WHERE title LIKE ? OR author LIKE ?";
        List<Book> searchResults = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
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
                book.setStatus(resultSet.getString("status"));
                searchResults.add(book);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    public void bookStatistics(Connection connection) {
        try {
            // Query to get the count of available books
            String availableBooksQuery = "SELECT COUNT(*) AS available_books FROM books WHERE status = 'Available'";
            PreparedStatement availableBooksStatement = connection.prepareStatement(availableBooksQuery);
            ResultSet availableBooksResult = availableBooksStatement.executeQuery();

            int availableBooksCount = 0;
            if (availableBooksResult.next()) {
                availableBooksCount = availableBooksResult.getInt("available_books");
            }

            // Query to get the count of borrowed books
            String borrowedBooksQuery = "SELECT COUNT(*) AS borrowed_books FROM books WHERE status = 'Checked Out'";
            PreparedStatement borrowedBooksStatement = connection.prepareStatement(borrowedBooksQuery);
            ResultSet borrowedBooksResult = borrowedBooksStatement.executeQuery();

            int borrowedBooksCount = 0;
            if (borrowedBooksResult.next()) {
                borrowedBooksCount = borrowedBooksResult.getInt("borrowed_books");
            }

            // Query to get the count of lost books
            String lostBooksQuery = "SELECT COUNT(*) AS lost_books FROM books WHERE status = 'Lost'";
            PreparedStatement lostBooksStatement = connection.prepareStatement(lostBooksQuery);
            ResultSet lostBooksResult = lostBooksStatement.executeQuery();

            int lostBooksCount = 0;
            if (lostBooksResult.next()) {
                lostBooksCount = lostBooksResult.getInt("lost_books");
            }

            // Display the book statistics report
            System.out.println("\t        ╔══════════════════════╦══════════════════╦══════════════════════╦══════════════════╦═════════════════════╦═══════════════╗");
            System.out.println("\t        ║            Total Available Books        ║            Total Borrowed Books         ║             Total Lost Books        ║");
            System.out.println("\t        ╠══════════════════════╬══════════════════╬══════════════════════╬══════════════════╬═════════════════════╬═══════════════╣");
            System.out.printf("\t        ║ %39s ║ %39s ║ %35s ║%n", availableBooksCount, borrowedBooksCount, lostBooksCount);
            System.out.println("\t        ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}