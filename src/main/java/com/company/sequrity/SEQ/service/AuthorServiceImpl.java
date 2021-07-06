package com.company.sequrity.SEQ.service;

import com.company.sequrity.SEQ.dao.AuthorDao;
import com.company.sequrity.SEQ.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDao dao;
    @Autowired
    //@Qualifier("authorDao")
    public void setDao(AuthorDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Author> findAll() {
        return dao.findAll();
    }

    @Override
    public Author findAuthorById(int id) {
        return dao.findById(id).get();
    }

    @Override
    public void saveAuthor(Author au) {
        dao.save(au);
    }

    @Override
    public void updateAuthor(int id, Author au) {
        Author aunew = dao.findById(id).get();
        if (aunew!=null){
            aunew.setName(au.getName());
            aunew.setCountry(au.getCountry());
            dao.save(au);
        }
    }

    @Override
    public void removingAuthor(int id) {
        dao.delete(dao.findById(id).get());
    }
}
