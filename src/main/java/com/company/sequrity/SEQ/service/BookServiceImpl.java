package com.company.sequrity.SEQ.service;

import com.company.sequrity.SEQ.dao.AuthorDao;
import com.company.sequrity.SEQ.dao.BookDao;
import com.company.sequrity.SEQ.model.Author;
import com.company.sequrity.SEQ.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao dao1;
    private AuthorDao dao2;
    @Autowired
    public void setDao2(AuthorDao dao2) {
        this.dao2 = dao2;
    }
    @Autowired
    public void setDao1(BookDao dao1) {
        this.dao1 = dao1;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList();
        try {

            dao1.findAll().forEach(books::add);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book findBookById(int id) {
        Book b = null;
        try {
            b = dao1.findById(id).get();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return b;
    }
    //автор включает в себя параметры
    @Override
    public void saveBook(Book b2) {
        if (b2!=null && b2.getAuthor()!=null){

            Author author = dao2.searchAuthor(b2.getAuthor().getName(), b2.getAuthor().getCountry());
            if (author!=null){
                b2.setAuthor_index(author.getId());
            }
            dao1.save(b2);
            //Author author = Arrays.asList(dao2.findOne(b2.getAuthor())
        }

    }

    @Override
    public void updateBook(int id, Book b) {
        if (id>=0 && b!=null){
            Book data = dao1.findById(id).get();
            Author author = dao2.findById(b.getAuthor().getId()).get();
            if (author!=null){
                data.setAuthor_index(author.getId());
                data.setAuthor(author);
            }
            dao1.save(data);
        }
    }

    @Override
    public void removingBook(int id) {
        dao1.deleteById(id);
    }

    @Override
    public List<Book> searching(String name) {
        List<Book> books = dao1.getBooksByName(name);
        for (Book b :books){
            b.setAuthor(dao2.getById(b.getAuthor_index()));
        }
        return books;
    }


}
