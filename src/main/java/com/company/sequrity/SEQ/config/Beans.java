package com.company.sequrity.SEQ.config;

import com.company.sequrity.SEQ.service.AuthorService;
import com.company.sequrity.SEQ.service.AuthorServiceImpl;
import com.company.sequrity.SEQ.service.BookService;
import com.company.sequrity.SEQ.service.BookServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class Beans {
    @Bean
    public BookService getBookService()
    {return new BookServiceImpl();}
    @Bean
    public AuthorService getAuthorService(){return new AuthorServiceImpl();
    }
}
