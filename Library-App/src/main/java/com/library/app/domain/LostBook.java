package com.library.app.domain;

import java.sql.*;
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
}
