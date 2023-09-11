package com.library.app;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBook {
    private int id;
    private String bookIsbn;
    private String bookTitle;
    private int borrowerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private String book_author;
    private String book_category;
    private int book_year;

    public BorrowedBook() {

    }
    public BorrowedBook(int id, String bookIsbn, String bookTitle, LocalDate borrowDate, LocalDate dueDate, String book_author, String book_category, int book_year) {
        this.id = id;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
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

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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

    public BorrowedBook borrowBook(Connection connection, int borrowerId, String isbn) {
        String checkAvailabilityQuery = "SELECT id, title, author, category, year FROM books WHERE isbn = ? AND quantity > 0";

        try (PreparedStatement checkAvailabilityStatement = connection.prepareStatement(checkAvailabilityQuery)) {
            checkAvailabilityStatement.setString(1, isbn);
            ResultSet resultSet = checkAvailabilityStatement.executeQuery();

            if (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                String bookAuthor = resultSet.getString("author");
                String bookCategory = resultSet.getString("category");
                int bookYear = resultSet.getInt("year");

                // Calculate the due date
                LocalDate currentDate = LocalDate.now();
                LocalDate dueDate = currentDate.plusWeeks(1);

                // Create a record of the borrowing with the due date
                String insertRecordQuery = "INSERT INTO borrowedBooks (id, book_isbn, book_title, borrower_id, borrow_date, due_date, book_author, book_category, book_year) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement insertRecordStatement = connection.prepareStatement(insertRecordQuery)) {
                    insertRecordStatement.setInt(1, bookId);
                    insertRecordStatement.setString(2, isbn);
                    insertRecordStatement.setString(3, bookTitle);
                    insertRecordStatement.setInt(4, borrowerId);

                    // Get the current date and set it in the timestamp as just the date (without time)
                    LocalDate borrowDate = LocalDate.now();
                    Date borrowDateSql = Date.valueOf(borrowDate);
                    insertRecordStatement.setDate(5, borrowDateSql);

                    // Get the due date and set it in the timestamp as just the date (without time)
                    Date dueDateSql = Date.valueOf(dueDate);
                    insertRecordStatement.setDate(6, dueDateSql);
                    insertRecordStatement.setString(7, bookAuthor);
                    insertRecordStatement.setString(8, bookCategory);
                    insertRecordStatement.setInt(9, bookYear);
                    insertRecordStatement.executeUpdate();

                    System.out.println("Book with ISBN " + isbn + " has been borrowed by borrower ID " + borrowerId + ".");
                    System.out.println("Due Date: " + dueDate);

                    // Create and return the borrowed book object without borrower ID
                    return new BorrowedBook(bookId, isbn, bookTitle, borrowDate, dueDate, bookAuthor, bookCategory, bookYear);
                }
            } else {
                System.out.println("Book with ISBN " + isbn + " is not available for borrowing.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the book was not borrowed
    }

    public BorrowedBook returnBook(Connection connection, int borrowerId, String isbn) {
        String selectRecordQuery = "SELECT * FROM borrowedBooks WHERE book_isbn = ? AND borrower_id = ?";

        try (PreparedStatement selectRecordStatement = connection.prepareStatement(selectRecordQuery)) {
            selectRecordStatement.setString(1, isbn);
            selectRecordStatement.setInt(2, borrowerId);

            ResultSet resultSet = selectRecordStatement.executeQuery();

            if (resultSet.next()) {
                int recordId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("book_title");
                String bookAuthor = resultSet.getString("book_author");
                String bookCategory = resultSet.getString("book_category");
                int bookYear = resultSet.getInt("book_year");
                LocalDate borrowDate = resultSet.getDate("borrow_date").toLocalDate();
                LocalDate dueDate = resultSet.getDate("due_date").toLocalDate();

                // Delete the record of the returned book
                String deleteRecordQuery = "DELETE FROM borrowedBooks WHERE id = ?";
                try (PreparedStatement deleteRecordStatement = connection.prepareStatement(deleteRecordQuery)) {
                    deleteRecordStatement.setInt(1, recordId);

                    int rowsAffected = deleteRecordStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Book with ISBN " + isbn + " has been returned by borrower ID " + borrowerId + ".");
                        return new BorrowedBook(recordId, isbn, bookTitle, borrowDate, dueDate, bookAuthor, bookCategory, bookYear);
                    }
                }
            } else {
                System.out.println("No matching record found for the book with ISBN " + isbn + " and borrower ID " + borrowerId + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<BorrowedBook> displayBorrowedBooks(Connection connection) {
        String query = "SELECT * FROM borrowedBooks";
        List<BorrowedBook> borrowedBookList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("book_title");
                String author = resultSet.getString("book_author");
                String isbn = resultSet.getString("book_isbn");
                String category = resultSet.getString("book_category");
                int year = resultSet.getInt("book_year");
                LocalDate borrowDate = resultSet.getDate("borrow_date").toLocalDate();
                LocalDate dueDate = resultSet.getDate("due_date").toLocalDate();

                BorrowedBook borrowedBook = new BorrowedBook(id, isbn, title, borrowDate, dueDate, author, category, year);
                borrowedBookList.add(borrowedBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowedBookList;
    }
}
