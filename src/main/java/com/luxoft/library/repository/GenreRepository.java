package com.luxoft.library.repository;

import com.luxoft.library.entities.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GenreRepository {

    Genre save(Genre book);

    List<Genre> findAll();

    Genre findById(UUID id);

    void delete(Genre book);

    void deleteAll();

    Genre findByName(String name);

    Genre saveAndFlush(Genre genre);

}
