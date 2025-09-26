package com.danlearnings.bookcatalog.api.service;

import com.danlearnings.bookcatalog.api.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAllBooks();

    Optional<Book> findBookById(Long id);

    Book saveBook(Book book);

    Optional<Book> updateBook(Long id, Book book);

    void deleteBookById(Long id);
}