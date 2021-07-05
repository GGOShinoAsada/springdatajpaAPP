package com.company.sequrity.SEQ.service;

import com.company.sequrity.SEQ.model.Author;
import com.company.sequrity.SEQ.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> findAll();
    Book findBookById(int id);
    void saveBook(Book b);
    void updateBook(int id, Book b);
    void removingBook(int id);
    List<Book> searching(String name);
}
