package com.library.app.services;

import com.library.app.domain.BorrowedBook;
import com.library.app.repository.BorrowedBooksRepository;

import java.util.List;

public class BorrowedBooksService {
    private BorrowedBooksRepository borrowedBooksRepository;

    public BorrowedBooksService(BorrowedBooksRepository bookRepository) {
        this.borrowedBooksRepository = bookRepository;
    }

    public BorrowedBook borrowBook(int borrowerId, String isbn) {
        return this.borrowedBooksRepository.borrowBook(borrowerId, isbn);
    }

    public BorrowedBook returnBook(int borrowerId, String isbn) {
        return this.borrowedBooksRepository.returnBook(borrowerId, isbn);
    }

    public List<BorrowedBook> displayBorrowedBooks() {
        return this.borrowedBooksRepository.displayBorrowedBooks();
    }
}
