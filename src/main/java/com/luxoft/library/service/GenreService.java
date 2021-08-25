package com.luxoft.library.service;

import com.luxoft.library.entities.Genre;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface GenreService {

    /**
     * Сохранение жанра.
     *
     * @param entity сущность жанра.
     * @return сохраненный жанр.
     */
    Genre save(Genre entity);

    /**
     * Проверка существоования жанра.
     *
     * @param genreName название жанра.
     * @param entity    жанр.
     * @return <code>true</code> если жанр существует.
     */
    boolean isGenreExists(String genreName, Genre entity);

    /**
     * Получение жанра по ID.
     *
     * @param genreId ID получаемого жанра.
     * @return жанр.
     */
    Optional<? extends Genre> get(UUID genreId);

    /**
     * Получение списка жанров.
     *
     * @return жанр.
     */
    Set<Genre> get();

    /**
     * Удалить жанр.
     *
     * @param genre удаляемая сущность.
     */
    void delete(Genre genre);

    /**
     * Найти жанр по названию.
     *
     * @param name название жанра.
     */
    Genre findByName(String name);

}
