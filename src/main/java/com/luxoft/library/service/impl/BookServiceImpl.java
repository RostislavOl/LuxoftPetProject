package com.luxoft.library.service.impl;

import com.luxoft.library.entities.Book;
import com.luxoft.library.exceptions.DataNotFoundedExceptions;
import com.luxoft.library.exceptions.constants.MessageResource;
import com.luxoft.library.repository.BookRepository;
import com.luxoft.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static com.luxoft.library.exceptions.constants.MessageResource.BOOK_BY_NAME_NOT_FOUND;

/**
 * Имплементация книжного сервиса.
 */
@RequiredArgsConstructor
@Transactional
@Component
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public Book save(Book entity) {
        return repository.save(entity);
    }

    @Override
    public boolean isBookExists(String bookName, Book entity) {
        return repository.existsByNameAndId(bookName, entity.getId());
    }

    @Override
    public Optional<? extends Book> get(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<? extends Book> get() {
        return repository.findAll();
    }

    @Override
    public void delete(Book book) {
        repository.delete(book);
    }

    @Override
    public Book findByName(String name) {
        return repository.findByName(name).orElseThrow(
                () -> new DataNotFoundedExceptions(BOOK_BY_NAME_NOT_FOUND, name)
        );
    }
}
