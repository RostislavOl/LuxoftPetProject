package com.luxoft.library.controller;

import com.luxoft.library.dto.CommentDTO;
import com.luxoft.library.service.CommentControllerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/{bookId}/comments")
@Tag(name = "Комментарии", description = "Набор операций для работы с коментариями к книге")
public class CommentsController {

    private final CommentControllerService service;

    /**
     * Добавляет новый коментарий к книге.
     *
     * @param comment данные комментария.
     * @return добавленный к книге комментарий.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(summary = "Добавление", description = "Добавление нового комментария к книге")
    CommentDTO add(@PathVariable("bookId") UUID bookId, @RequestBody CommentDTO comment) {
        return service.add(bookId, comment);
    }

    /**
     * Редактирование комментария.
     *
     * @param bookId    ID книги.
     * @param commentId ID изменяемого комментария.
     * @param comment   измененный коментарий.
     */
    @PutMapping(value = "{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменение", description = "Изменение комментария")
    void edit(
            @PathVariable("bookId") UUID bookId,
            @PathVariable("commentId") UUID commentId,
            @RequestBody CommentDTO comment
    ) {
        service.edit(bookId, commentId, comment);
    }

    /**
     * Удаление комментария по ID.
     *
     * @param bookId    ID книги, которой принадлежит комментарий.
     * @param commentId ID удаляемого комментария.
     */
    @DeleteMapping("{commentId}")
    @Operation(summary = "Удаление", description = "Удаление комментария к книге")
    void delete(
            @PathVariable("bookId") UUID bookId,
            @PathVariable("commentId") UUID commentId
    ) {
        service.delete(bookId, commentId);
    }

    /**
     * Получение комментария по ID.
     *
     * @param bookId    ID книги, к которой был оставлен комментарий.
     * @param commentId ID комментария.
     * @return комментарий.
     */
    @GetMapping(value = "{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(summary = "Получение", description = "Получение комментария к книге")
    CommentDTO get(
            @PathVariable("bookId") UUID bookId,
            @PathVariable("commentId") UUID commentId
    ) {
        return service.get(bookId, commentId);
    }

    /**
     * Получить все комментарии к книге.
     *
     * @param bookId ID книги.
     * @return коллекцию комментариев.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(summary = "Получение всех", description = "Получение всех комментариев к книге")
    Collection<CommentDTO> getAll(@PathVariable("bookId") UUID bookId) {
        return service.get(bookId);
    }

}
