package com.luxoft.library.controller;

import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.NewBookDTO;
import com.luxoft.library.service.BookControllerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

/**
 * Контроллер книг
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookControllerService service;

    /**
     * Добавление новой книги.
     *
     * @param book сущность книги.
     *
     * @return добавленую книгу.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавление", description = "Добавление новой книги")
    public BookDTO add(@RequestBody NewBookDTO book) {
        return service.add(book);
    }

    /**
     * Изменение сущности книги.
     *
     * @param bookId ID книги.
     * @param book измененные данные.
     */
    @PutMapping(value = "{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменение", description = "Изменение данных книги")
    public void edit(@PathVariable("bookId") UUID bookId, @RequestBody NewBookDTO book) {
        service.edit(bookId, book);
    }

    /**
     * Удаление книги по ID.
     *
     * @param bookId ID книги, которую нужно удалить.
     */
    @DeleteMapping("{bookId}")
    @Operation(summary = "Удаление", description = "Удаление книги")
    public void delete(@PathVariable("bookId") UUID bookId) {
        service.delete(bookId);
    }

    /**
     * Получение книги по ID.
     *
     * @param bookId ID книги.
     *
     * @return книгу.
     */
    @GetMapping(value = "{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение", description = "Получение книги по ID")
    public BookDTO get(@PathVariable UUID bookId) {
        return service.get(bookId);
    }

    /**
     * Получение всех книг.
     *
     * @return коллекцию книг.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение всех", description = "Получение данных всех книг в библиотеке")
    public Collection<BookDTO> getAll() {
        return service.get();
    }
}
