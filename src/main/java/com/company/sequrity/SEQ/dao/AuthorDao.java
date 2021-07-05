package com.company.sequrity.SEQ.dao;

import com.company.sequrity.SEQ.model.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {
@Query("select c from authors c where c.name=?1 and c.country=?2")
Author searchAuthor(@Param("1") String name, @Param("2") String country);
}
