package com.jackson.springboot_graphql_crud.repository;

import com.jackson.springboot_graphql_crud.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByAuthor(String author);
    List<BookEntity> findByTitleContaining(String title);
}