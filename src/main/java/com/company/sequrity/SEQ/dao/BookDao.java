package com.company.sequrity.SEQ.dao;

import com.company.sequrity.SEQ.model.Author;
import com.company.sequrity.SEQ.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.*;
public interface BookDao extends CrudRepository<Book, Integer> {
    /*@Query("select d from authors d join book b  where b.author_index = d.id and b.id=:bid")
    Author getAuthorById(@Param("id") int bid);*/
    @Query("select b from book b where b.name=:n")
    List<Book> getBooksByName(@Param("n") String n);
}
