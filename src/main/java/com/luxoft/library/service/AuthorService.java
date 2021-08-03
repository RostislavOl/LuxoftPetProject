package com.luxoft.library.service;

import com.luxoft.library.dto.AuthorDTO;

import java.util.Collection;
import java.util.UUID;

public interface AuthorService {

    /**
     * Добавление нового автора.
     *
     * @param author данные нового автора.
     * @return добавленного автора.
     */
    AuthorDTO add(AuthorDTO author);

    /**
     * Изменение данных автора.
     *
     * @param authorId ID автора.
     * @param author   измененные данные автора.
     */
    void edit(UUID authorId, AuthorDTO author);

    /**
     * Удаление автора.
     *
     * @param authorId ID удаляемого автора.
     */
    void delete(UUID authorId);

    /**
     * Получение автора по ID.
     *
     * @param authorId ID получаемого автора.
     * @return книгу.
     */
    AuthorDTO get(UUID authorId);

    /**
     * Получение всех авторов.
     *
     * @return коллекцию авторов.
     */
    Collection<AuthorDTO> get();

}
