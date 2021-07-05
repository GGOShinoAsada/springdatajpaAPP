package com.company.sequrity.SEQ.controller;


import com.company.sequrity.SEQ.model.Book;
import com.company.sequrity.SEQ.service.AuthorService;
import com.company.sequrity.SEQ.service.AuthorServiceImpl;
import com.company.sequrity.SEQ.service.BookService;
import com.company.sequrity.SEQ.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.*;
@Controller
@RequestMapping("/books")
public class BookController {


    public BookService service;
    private AuthorService ser;
    @Autowired
    public void setService(@Qualifier("getBookService") BookService service) {
        this.service = service;
    }
    @Autowired
    public void setSer(@Qualifier("getAuthorService") AuthorService ser) {
        this.ser = ser;
    }



    @GetMapping("/index")
    private String index(Model model) {
        model.addAttribute("books", service.findAll());
        return "books/index";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") int id, Model model){
        Book b = service.findBookById(id);
        b.setAuthor(ser.findAuthorById(b.getAuthor_index()));
        model.addAttribute("book", b );
        //model.addAttribute("author", ser.getAuthorById(b.getAuthor_id()));
        return "books/details";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("authors", ser.findAll());
        return "books/create";
    }
    @PostMapping("/create")
    public String createPost(@Valid String authors, Book book){
        if (authors!=""){
            int auid = Integer.parseInt(authors);
            book.setAuthor_index(auid);
            book.setAuthor(ser.findAuthorById(auid));
            service.saveBook(book);
        }
        else {
            //do something
        }


        return "redirect:/books/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", service.findBookById(id));
        model.addAttribute("authors", ser.findAll());
        return "books/edit";
    }
    @PostMapping("/edit")
    public String editpost(@Valid String authors, Book book){
        if (authors!=""){
            Integer auid = Integer.parseInt(authors);
            book.setAuthor_index(auid);
            book.setAuthor(ser.findAuthorById(auid));
            service.updateBook(book.getId(), book);
        }
       else {
           //do something
        }
        return "redirect:/books/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteget(@PathVariable("id") int id){
        service.removingBook(id);
        return "redirect:/books/index";
    }

}
