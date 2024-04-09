package com.prabhav.LibraryManagement.Service;

import com.prabhav.LibraryManagement.Entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
    Book borrowBook(Long bookId, String name);
    Book returnBook(Long bookId);
    void createBook(Book book);
}
