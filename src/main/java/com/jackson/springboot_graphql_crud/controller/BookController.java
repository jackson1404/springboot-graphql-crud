package com.jackson.springboot_graphql_crud.controller;

import com.jackson.springboot_graphql_crud.model.BookEntity;
import com.jackson.springboot_graphql_crud.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @QueryMapping
    public List<BookEntity> booksByAuthor(@Argument String author){
        System.out.println("the author " + author);
        return bookRepository.findByAuthor(author);
    }

//    @QueryMapping
//    public List<BookEntity> booksByAuthor(@Argument String author) {
//        System.out.println("the author " + author);
//        return bookRepository.findByAuthor(author);
//    }
//
    @MutationMapping
    public BookEntity addBook(@Argument String title,
                        @Argument String author,
                        @Argument Integer pages,
                        @Argument String isbn) {
        BookEntity book = new BookEntity(null, title, author, pages, isbn);
        return bookRepository.save(book);
    }


}
