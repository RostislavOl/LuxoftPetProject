package com.luxoft.library.service;

import com.luxoft.library.dto.GenreDTO;

import java.util.Set;
import java.util.UUID;

/**
 * Сервис для работы с контроллером жанров
 */
public interface GenreControllerService {

    /**
     * Добавление нового жанра.
     *
     * @param genre данные нового жанра.
     * @return добавленный жанр.
     */
    GenreDTO add(GenreDTO genre);

    /**
     * Изменение данных жанра.
     *
     * @param genreId ID жанра.
     * @param genre   измененные данные жанра.
     */
    void edit(UUID genreId, GenreDTO genre);

    /**
     * Удаление жанра.
     *
     * @param genreId ID удаляемого жанра.
     */
    void delete(UUID genreId);

    /**
     * Получение жанра ID.
     *
     * @param genreId ID получаемого жанра.
     * @return жанр.
     */
    GenreDTO get(UUID genreId);

    /**
     * Получение всех жанров.
     *
     * @return коллекцию жанров.
     */
    Set<GenreDTO> get();

}
