package com.luxoft.library.service.impl;

import com.luxoft.library.dto.AuthorDTO;
import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.entities.Author;
import com.luxoft.library.entities.Book;
import com.luxoft.library.exceptions.DataNotFoundedExceptions;
import com.luxoft.library.exceptions.DuplicateDataException;
import com.luxoft.library.repository.AuthorRepository;
import com.luxoft.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Имплементация сервиса авторов.
 */
@RequiredArgsConstructor
@Component
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public AuthorDTO add(AuthorDTO author) {
        var entity = Author.builder()
                .id(UUID.randomUUID())
                .fullName(author.getFullName())
                .build();
        repository.save(entity);
        return createResponse(entity);
    }

    @Override
    public void edit(UUID authorId, AuthorDTO author) {
        var entity = repository.findById(authorId);
        if (!repository.existsByNameAndId(author.getFullName(), author.getId())) {
            throw new DuplicateDataException(author.getId(), entity.getFullName(), "name", entity.getFullName());
        }

        entity =
                entity.toBuilder()
                        .fullName(entity.getFullName())
                        .build();

        repository.save(entity);
    }

    @Override
    public void delete(UUID authorId) {
        var author =
                repository.findById(authorId);
        repository.delete(author);
    }

    @Override
    public AuthorDTO get(UUID authorId) {
        return createResponse(repository.findById(authorId));
    }

    @Override
    public Collection<AuthorDTO> get() {
        return repository.findAll().stream().map(this::createResponse).collect(Collectors.toList());
    }

    private AuthorDTO createResponse(Author entity) {
        return AuthorDTO.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .build();
    }
}
