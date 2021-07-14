package com.luxoft.library.controller.impl;

import com.luxoft.library.controller.BookController;
import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.NewBookDTO;
import com.luxoft.library.service.BookControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

/**
 * Имплементация контроллера для книг.
 */
@RequiredArgsConstructor
@RestController
public class BookControllerImpl implements BookController {

    private final BookControllerService service;

    @Override
    public BookDTO add(NewBookDTO book) {
        return service.add(book);
    }

    @Override
    public void edit(UUID bookId, NewBookDTO book) {
        service.edit(bookId, book);
    }

    @Override
    public void delete(UUID bookId) {
        service.delete(bookId);
    }

    @Override
    public BookDTO get(UUID bookId) {
        return service.get(bookId);
    }

    @Override
    public Collection<? extends BookDTO> getAll() {
        return service.get();
    }
}
