package com.luxoft.library.service.impl;

import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.NewBookDTO;
import com.luxoft.library.entities.Book;
import com.luxoft.library.exceptions.DataNotFoundedExceptions;
import com.luxoft.library.exceptions.DuplicateDataException;
import com.luxoft.library.service.BookControllerService;
import com.luxoft.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Имплементация книжного контроллера.
 */
@RequiredArgsConstructor
@Component
public class BookControllerServiceImpl implements BookControllerService {

    private final BookService service;

    @Override
    public BookDTO add(NewBookDTO book) {
        var entity = Book.builder()
                                    .id(UUID.randomUUID())
                                    .author(book.getAuthor())
                                    .comment(book.getComment())
                                    .name(book.getName())
                                    .genre(book.getGenre())
                                    .build();
        service.save(entity);
        return createResponse(entity);
    }

    @Override
    public void edit(UUID bookId, NewBookDTO book) {
        var entity = service.get(bookId).orElseThrow(() -> new DataNotFoundedExceptions(Book.class.getSimpleName(), bookId));
        if (!service.isBookExists(book.getName(), entity)) {
            throw new DuplicateDataException(bookId, book.getName(), "name", book.getName());
        }

        entity =
                entity.toBuilder().name(book.getName())
                        .author(book.getAuthor())
                        .comment(book.getComment())
                        .genre(book.getGenre())
                        .build();

        service.save(entity);
    }

    @Override
    public void delete(UUID bookId) {
        var book =
                service.get(bookId).orElseThrow(() -> new DataNotFoundedExceptions(Book.class.getName(), bookId));
        service.delete(book);
    }

    @Override
    public BookDTO get(UUID bookId) {
        return service.get(bookId).map(this::createResponse)
                .orElseThrow(() -> new DataNotFoundedExceptions(Book.class.getName(), bookId));
    }

    @Override
    public Collection<? extends BookDTO> get() {
        return service.get().stream().map(this::createResponse).collect(Collectors.toList());
    }

    private BookDTO createResponse(Book entity) {
        return BookDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
