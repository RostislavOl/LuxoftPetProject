package com.luxoft.library.service;

import com.luxoft.library.dto.BookDTO;
import com.luxoft.library.dto.NewBookDTO;

import java.util.Collection;
import java.util.UUID;

/**
 * Сервис для работы с контроллером книг
 */
public interface BookControllerService {

    /**
     * Добавление новой книги.
     *
     * @param book данные новой книги.
     *
     * @return добавленную книгу.
     */
    BookDTO add(NewBookDTO book);

    /**
     * Изменение данных книги.
     *
     * @param bookId ID книги.
     * @param book измененные данные книги.
     */
    void edit(UUID bookId, NewBookDTO book);

    /**
     * Удаление книги из библиотеки.
     *
     * @param bookId ID удаляемой книги.
     */
    void delete(UUID bookId);

    /**
     * Получение книги ID.
     *
     * @param bookId ID получаемой книги.
     *
     * @return книгу.
     */
    BookDTO get(UUID bookId);

    /**
     * Получение всех книг.
     *
     * @return коллекцию книг.
     */
    Collection<? extends BookDTO> get();

}
