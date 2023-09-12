package com.library.app.repository;

import com.library.app.domain.BorrowedBook;
import com.library.app.domain.enums.Colors;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowedBooksRepository {

    private Connection dbConnection;
    public BorrowedBooksRepository(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }
    public BorrowedBook borrowBook(int borrowerId, String isbn) {
        String checkAvailabilityQuery = "SELECT id, title, author, category, year FROM books WHERE isbn = ? AND quantity > 0";

        try (PreparedStatement checkAvailabilityStatement = dbConnection.prepareStatement(checkAvailabilityQuery)) {
            checkAvailabilityStatement.setString(1, isbn);
            ResultSet resultSet = checkAvailabilityStatement.executeQuery();

            if (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                String bookAuthor = resultSet.getString("author");
                String bookCategory = resultSet.getString("category");
                int bookYear = resultSet.getInt("year");

                if (hasBorrowedBook(borrowerId, isbn)) {
                    System.out.println(Colors.BROWN.getColor() +"\n\nYou have already borrowed this book.");
                    return null;
                }

                LocalDate currentDate = LocalDate.now();
                LocalDate dueDate = currentDate.plusWeeks(1);

                String insertRecordQuery = "INSERT INTO borrowedBooks (id, book_isbn, book_title, borrower_id, borrow_date, due_date, book_author, book_category, book_year) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement insertRecordStatement = dbConnection.prepareStatement(insertRecordQuery)) {
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

                    System.out.println(Colors.GREEN.getColor() +"\n\nBook with ISBN " + isbn + " has been borrowed by borrower ID " + borrowerId + ".");
                    System.out.println(Colors.GREEN.getColor() +"\nDue Date: " + dueDate);

                    // Create and return the borrowed book object without borrower ID
                    return new BorrowedBook(bookId, isbn, bookTitle, borrowDate, dueDate, bookAuthor, bookCategory, bookYear);
                }
            } else {
                System.out.println(Colors.RED.getColor() +"\n\nBook with ISBN " + isbn + " is not available for borrowing.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private boolean hasBorrowedBook(int borrowerId, String isbn) {
        String checkBorrowedQuery = "SELECT COUNT(*) AS book_count FROM borrowedBooks WHERE borrower_id = ? AND book_isbn = ?";

        try (PreparedStatement checkBorrowedStatement = dbConnection.prepareStatement(checkBorrowedQuery)) {
            checkBorrowedStatement.setInt(1, borrowerId);
            checkBorrowedStatement.setString(2, isbn);
            ResultSet resultSet = checkBorrowedStatement.executeQuery();

            if (resultSet.next()) {
                int bookCount = resultSet.getInt("book_count");
                return bookCount > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public BorrowedBook returnBook(int borrowerId, String isbn) {
        String selectRecordQuery = "SELECT * FROM borrowedBooks WHERE book_isbn = ? AND borrower_id = ?";

        try (PreparedStatement selectRecordStatement = dbConnection.prepareStatement(selectRecordQuery)) {
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
                try (PreparedStatement deleteRecordStatement = dbConnection.prepareStatement(deleteRecordQuery)) {
                    deleteRecordStatement.setInt(1, recordId);

                    int rowsAffected = deleteRecordStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println(Colors.GREEN.getColor() +"\n\nBook with ISBN " + isbn + " has been returned by borrower ID " + borrowerId + ".");
                        return new BorrowedBook(recordId, isbn, bookTitle, borrowDate, dueDate, bookAuthor, bookCategory, bookYear);
                    }
                }
            } else {
                System.out.println(Colors.RED.getColor() +"\n\nNo matching record found for the book with ISBN " + isbn + " and borrower ID " + borrowerId + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<BorrowedBook> displayBorrowedBooks() {
        String query = "SELECT * FROM borrowedBooks";
        List<BorrowedBook> borrowedBookList = new ArrayList<>();

        try (Statement statement = dbConnection.createStatement();
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
