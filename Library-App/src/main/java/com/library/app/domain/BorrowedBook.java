package com.library.app.domain;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
}
