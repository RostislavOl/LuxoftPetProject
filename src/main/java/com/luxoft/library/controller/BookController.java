package com.luxoft.library.controller;

import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.NewBookDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

/**
 * Контроллер книг
 */
@RequestMapping("/book")
public interface BookController {

    /**
     * Добавление новой книги.
     *
     * @param book сущность книги.
     *
     * @return добавленую книгу.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(summary = "Добавление", description = "Добавление новой книги")
    BookDTO add(@RequestBody NewBookDTO book);

    /**
     * Изменение сущности книги.
     *
     * @param bookId ID книги.
     * @param book измененные данные.
     */
    @PutMapping(value = "{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменение", description = "Изменение данных книги")
    void edit(
            @PathVariable("bookId") UUID bookId,
            @RequestBody NewBookDTO book
    );

    /**
     * Удаление книги по ID.
     *
     * @param bookId ID книги, которую нужно удалить.
     */
    @DeleteMapping("{bookId}")
    @Operation(summary = "Удаление", description = "Удаление книги")
    void delete(
            @PathVariable("bookId") UUID bookId
    );

    /**
     * Получение книги по ID.
     *
     * @param bookId ID книги.
     *
     * @return книгу.
     */
    @GetMapping(value = "{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(summary = "Получение", description = "Получение книги по ID")
    BookDTO get(
            @PathVariable("bookId") UUID bookId
    );

    /**
     * Получение всех книг.
     *
     * @return коллекцию книг.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(summary = "Получение всех", description = "Получение данных всех книг в библиотеке")
    Collection<? extends BookDTO> getAll();

}
