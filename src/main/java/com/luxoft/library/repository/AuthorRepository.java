package com.luxoft.library.repository;

import com.luxoft.library.entities.Author;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface AuthorRepository {

    Author save(Author author);

    List<Author> findAll();

    Author findById(UUID id);

    void delete(Author author);

    void deleteAll();

    Author findByName(String name);

    Author saveAndFlush(Author author);

    boolean existsByNameAndId(String name, UUID id);

}
