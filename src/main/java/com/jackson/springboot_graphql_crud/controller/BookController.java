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
@SuppressWarnings("unused")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @QueryMapping
    public List<BookEntity> booksByAuthor(@Argument String author){
        System.out.println("the author " + author);
        return bookRepository.findByAuthor(author);
    }

    @QueryMapping
    public List<BookEntity> findAllBooks(){
        System.out.println("the all books" );
        return bookRepository.findAll();
    }

    @QueryMapping
    public List<BookEntity> findBookByTitle(@Argument String title){
        return bookRepository.findByTitleContaining(title);
    }

    @QueryMapping
    public BookEntity findBookByBookId(@Argument Long bookId){
        return bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("No found"));
    }

    @MutationMapping
    public BookEntity addBook(@Argument String title,
                        @Argument String author,
                        @Argument Integer pages,
                        @Argument String isbn) {
        BookEntity book = new BookEntity(null, title, author, pages, isbn);
        return bookRepository.save(book);
    }

    @MutationMapping
    public BookEntity updateBook(@Argument Long bookId,
                                 @Argument String title,
                                 @Argument String author,
                                 @Argument Integer pages,
                                 @Argument String isBn){
        BookEntity book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Not found"));
        if (title!= null) book.setTitle(title);
        if (author != null) book.setAuthor(author);
        if (pages != null) book.setPages(pages);
        if (isBn != null) book.setIsbn(isBn);
        return bookRepository.save(book);
    }


}
