package com.company.sequrity.SEQ.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "book")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "ISBN", nullable = false)
    private String isbn;
    @Column(name = "author_index", nullable = false)
    private int author_index;
    @ManyToOne
    @JoinColumn(name="author_fk", nullable=false)
    private Author author;
}
