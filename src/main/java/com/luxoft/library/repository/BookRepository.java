package com.luxoft.library.repository;

import com.luxoft.library.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository {

    Book save(Book book);

    List<Book> findAll();

    Book findById(UUID id);

    void delete(Book book);

    void deleteAll();

    Book findByName(String name);

    boolean existsByNameAndId(String name, UUID id);

}
