package com.luxoft.library.service.impl;

import com.luxoft.library.aspects.interfaces.LogExecutionTime;
import com.luxoft.library.dto.GenreDTO;
import com.luxoft.library.entities.Genre;
import com.luxoft.library.exceptions.DataNotFoundedExceptions;
import com.luxoft.library.exceptions.DuplicateDataException;
import com.luxoft.library.service.GenreControllerService;
import com.luxoft.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Имплементация книжного контроллера.
 */
@RequiredArgsConstructor
@Component
public class GenreControllerServiceImpl implements GenreControllerService {

    private final GenreService service;

    @Override
    @LogExecutionTime
    public GenreDTO add(GenreDTO genre) {
        var entity = Genre.builder()
                .id(UUID.randomUUID())
                .genreName(genre.getBody())
                .build();
        service.save(entity);
        return createResponse(entity);
    }

    @Override
    @Transactional
    public void edit(UUID genreId, GenreDTO genre) {
        var entity = service.get(genreId).orElseThrow(() -> new DataNotFoundedExceptions(Genre.class.getSimpleName(), genreId));
        if (!service.isGenreExists(genre.getBody(), entity)) {
            throw new DuplicateDataException(genreId, genre.getBody(), "name", genre.getBody());
        }

        entity =
                entity.toBuilder()
                        .id(genre.getId())
                        .genreName(genre.getBody())
                        .build();

        service.save(entity);
    }

    @Override
    @LogExecutionTime
    public void delete(UUID genreId) {
        var genre =
                service.get(genreId).orElseThrow(() -> new DataNotFoundedExceptions(Genre.class.getName(), genreId));
        service.delete(genre);
    }

    @Override
    @LogExecutionTime
    public GenreDTO get(UUID genreId) {
        return service.get(genreId).map(this::createResponse)
                .orElseThrow(() -> new DataNotFoundedExceptions(Genre.class.getName(), genreId));
    }

    @Override
    @LogExecutionTime
    public Set<GenreDTO> get() {
        return service.get().stream().map(this::createResponse).collect(Collectors.toSet());
    }

    private GenreDTO createResponse(Genre entity) {
        return GenreDTO.builder()
                .id(entity.getId())
                .body(entity.getGenreName())
                .build();
    }
}
