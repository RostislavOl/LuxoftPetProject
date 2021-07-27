package com.luxoft.library.service.impl;

import com.luxoft.library.entities.Genre;
import com.luxoft.library.repository.GenreRepository;
import com.luxoft.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Имплементация сервиса жанров.
 */
@RequiredArgsConstructor
@Component
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    public Genre save(Genre entity) {
        return repository.save(entity);
    }

    @Override
    public boolean isGenreExists(String genreName, Genre entity) {
        return repository.existsByNameAndId(genreName, entity.getId());
    }

    @Override
    public Optional<? extends Genre> get(UUID genreId) {
        return Optional.ofNullable(repository.findById(genreId));
    }

    @Override
    public Set<Genre> get() {
        return repository.findAll();
    }

    @Override
    public void delete(Genre genre) {
        repository.delete(genre);
    }

    @Override
    public Genre findByName(String name) {
        return repository.findByName(name);
    }
}
