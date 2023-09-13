package com.library.app.services;

import com.library.app.domain.LostBook;
import com.library.app.repository.LostBooksRepository;

import java.util.List;

public class LostBooksService {
    private LostBooksRepository lostBooksRepository;

    public LostBooksService(LostBooksRepository lostBookRepository) {
        this.lostBooksRepository = lostBookRepository;
    }

    public List<LostBook> displayLostBooks() {
        return this.lostBooksRepository.displayLostBooks();
    }

    public void insertLostBooks() {
        this.lostBooksRepository.insertLostBooks();
    }
}
