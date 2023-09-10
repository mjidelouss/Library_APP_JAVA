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
import java.util.List;

public class BorrowedBook {
    private int id;
    private String bookIsbn;
    private String bookTitle;
    private int borrowerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public BorrowedBook() {

    }
    public BorrowedBook(int id, String bookIsbn, String bookTitle, LocalDate borrowDate, LocalDate dueDate) {
        this.id = id;
        this.bookIsbn = bookIsbn;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

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

    public BorrowedBook borrowBook(Connection connection, int borrowerId, String isbn) {
        String checkAvailabilityQuery = "SELECT id, title FROM books WHERE isbn = ? AND quantity > 0";

        try (PreparedStatement checkAvailabilityStatement = connection.prepareStatement(checkAvailabilityQuery)) {
            checkAvailabilityStatement.setString(1, isbn);
            ResultSet resultSet = checkAvailabilityStatement.executeQuery();

            if (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");

                // Calculate the due date
                LocalDate currentDate = LocalDate.now();
                LocalDate dueDate = currentDate.plusWeeks(1);

                // Create a record of the borrowing with the due date
                String insertRecordQuery = "INSERT INTO borrowedBooks (book_isbn, book_title, borrower_id, borrow_date, due_date) VALUES (?, ?, ?, ?, ?)";

                try (PreparedStatement insertRecordStatement = connection.prepareStatement(insertRecordQuery)) {
                    insertRecordStatement.setString(1, isbn);
                    insertRecordStatement.setString(2, bookTitle);
                    insertRecordStatement.setInt(3, borrowerId);

                    // Get the current date and set it in the timestamp as just the date (without time)
                    LocalDate borrowDate = LocalDate.now();
                    Date borrowDateSql = Date.valueOf(borrowDate);
                    insertRecordStatement.setDate(4, borrowDateSql);

                    // Get the due date and set it in the timestamp as just the date (without time)
                    Date dueDateSql = Date.valueOf(dueDate);
                    insertRecordStatement.setDate(5, dueDateSql);
                    insertRecordStatement.executeUpdate();

                    System.out.println("Book with ISBN " + isbn + " has been borrowed by borrower ID " + borrowerId + ".");
                    System.out.println("Due Date: " + dueDate);

                    // Create and return the borrowed book object without borrower ID
                    return new BorrowedBook(bookId, isbn, bookTitle, borrowDate, dueDate);
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
                LocalDate borrowDate = resultSet.getDate("borrow_date").toLocalDate();
                LocalDate dueDate = resultSet.getDate("due_date").toLocalDate();

                // Delete the record of the returned book
                String deleteRecordQuery = "DELETE FROM borrowedBooks WHERE id = ?";
                try (PreparedStatement deleteRecordStatement = connection.prepareStatement(deleteRecordQuery)) {
                    deleteRecordStatement.setInt(1, recordId);

                    int rowsAffected = deleteRecordStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Book with ISBN " + isbn + " has been returned by borrower ID " + borrowerId + ".");
                        return new BorrowedBook(recordId, isbn, bookTitle, borrowDate, dueDate);
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
        String query = "SELECT * FROM books WHERE status = 'Available'";
    }
}
