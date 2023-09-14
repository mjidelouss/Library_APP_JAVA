package com.library.app.services;

import com.library.app.domain.Book;
import com.library.app.repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return this.bookRepository.addBook(book);
    }

    public Book updateBook(String oldIsbn, Book book) {
        return this.bookRepository.updateBook(oldIsbn, book);
    }

    public boolean deleteBook(String isbn, int quantity) {
        return this.bookRepository.deleteBook(isbn, quantity);
    }

    public List<Book> displayBooks() {
        return this.bookRepository.displayBooks();
    }

    public List<Book> searchBook(String searchTerm) {
        return this.bookRepository.searchBook(searchTerm);
    }

    public int[] bookStatistics() {
        return this.bookRepository.bookStatistics();
    }

    public int getBookQuantity(String isbn) {
        return this.bookRepository.getBookQuantity(isbn);
    }

    public Boolean isValidBookData(String title, String author, String isbn, String category, int year, int quantity) {
        if (title != null && author != null && isbn != null && category != null && year != 0 && quantity != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidISBN(String isbn) {
        String regex = "^[A-Z]\\d{0,9}$";
        return isbn.matches(regex);
    }
}
