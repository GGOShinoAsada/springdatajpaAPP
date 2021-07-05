package com.company.sequrity.SEQ.service;

import com.company.sequrity.SEQ.model.Author;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface AuthorService {
    List<Author> findAll();
    Author findAuthorById(int id);
    void saveAuthor(Author au);
    void updateAuthor(int id, Author au);
    void removingAuthor(int id);
}
