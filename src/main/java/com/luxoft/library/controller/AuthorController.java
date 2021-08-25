package com.luxoft.library.controller;

import com.luxoft.library.dto.AuthorDTO;
import com.luxoft.library.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Контроллер авторов
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    /**
     * Добавление нового автора.
     *
     * @param author автор.
     * @return добавленый автор.
     */
    @PostMapping
    @Operation(summary = "Добавление", description = "Добавление нового автора")
    public String add(@RequestBody AuthorDTO author) {
        service.add(author);
        return "redirect:/authors";
    }

    /**
     * Изменение автора.
     *
     * @param authorId ID автора.
     * @param author   измененные данные.
     */
    @PutMapping(value = "{authorId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменение", description = "Изменение данных автора")
    public void edit(@PathVariable("authorId") UUID authorId, @RequestBody AuthorDTO author) {
        service.edit(authorId, author);
    }

    /**
     * Удаление автора по ID.
     *
     * @param authorId ID автора, которого нужно удалить.
     */
    @DeleteMapping("{authorId}")
    @Operation(summary = "Удаление", description = "Удаление автора")
    public void delete(@PathVariable("authorId") UUID authorId) {
        service.delete(authorId);
    }

    /**
     * Получение автора по ID.
     *
     * @param authorId ID автора.
     * @return автора.
     */
    @GetMapping(value = "{authorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение", description = "Получение автора по ID")
    public AuthorDTO get(@PathVariable UUID authorId) {
        return service.get(authorId);
    }

    /**
     * Получение всех авторов.
     *
     * @return коллекцию авторов.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение всех", description = "Получение списка всех авторов")
    public String getAll(Model model) {
        model.addAttribute("author", service.get());
        return "author_page";
    }

}
