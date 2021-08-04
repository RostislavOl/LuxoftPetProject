package com.luxoft.library.controller;

import com.luxoft.library.dto.GenreDTO;
import com.luxoft.library.service.GenreControllerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Контроллер книг
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/genre")
public class GenreController {

    private final GenreControllerService service;

    /**
     * Добавление нового жанра.
     *
     * @param genre жанр.
     * @return добавленый жанр.
     */
    @PostMapping//(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Добавление", description = "Добавление нового жанра")
    public String add(@ModelAttribute @RequestBody GenreDTO genre) {
        service.add(genre);
        return "redirect:/genre";
    }

    /**
     * Изменение жанра.
     *
     * @param genreId ID жанра.
     * @param genre   измененные данные.
     */
    @PutMapping(value = "{genreId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Изменение", description = "Изменение данных книги")
    public void edit(@PathVariable("genreId") UUID genreId, @RequestBody GenreDTO genre) {
        service.edit(genreId, genre);
    }

    /**
     * Удаление жанра по ID.
     *
     * @param genreId ID жанра, который нужно удалить.
     */
    @DeleteMapping("{genreId}")
    @Operation(summary = "Удаление", description = "Удаление жанра")
    public void delete(@PathVariable("genreId") UUID genreId) {
        service.delete(genreId);
    }

    /**
     * Получение жанра по ID.
     *
     * @param genreId ID жанра.
     * @return жанр.
     */
    @GetMapping(value = "{genreId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение", description = "Получение жанра по ID")
    public GenreDTO get(@PathVariable UUID genreId) {
        return service.get(genreId);
    }

    /**
     * Получение всех жанров.
     *
     * @return коллекцию жанр.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение всех", description = "Получение списка всех жанров")
    public String getAll(Model model) {
        model.addAttribute("genre", service.get());
        return "genres_page";
    }

}
