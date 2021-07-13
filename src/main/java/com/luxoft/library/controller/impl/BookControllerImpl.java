package com.luxoft.library.controller.impl;

import com.luxoft.library.controller.BookController;
import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.NewBookDTO;
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

    @Override
    public BookDTO add(NewBookDTO book) {
        return null;
    }

    @Override
    public void edit(UUID bookId, NewBookDTO book) {

    }

    @Override
    public void delete(UUID bookId) {

    }

    @Override
    public BookDTO get(UUID bookId) {
        return null;
    }

    @Override
    public Collection<? extends BookDTO> getAll() {
        return null;
    }
}
