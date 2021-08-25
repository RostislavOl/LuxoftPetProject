package com.luxoft.library.service;

import com.luxoft.library.dto.CommentDTO;

import java.util.Collection;
import java.util.UUID;

/**
 * Сервис для работы с контроллером коментариев к книге
 */
public interface CommentControllerService {

    /**
     * Добавление нового комментария.
     *
     * @param bookId ID книги, к которой оставлен комментарий
     * @param comment данные нового комментария.
     *
     * @return добавленный комментарий.
     */
    CommentDTO add(UUID bookId, CommentDTO comment);

    /**
     * Изменение данных книги.
     *
     * @param bookId ID книги.
     * @param commentId ID комментария.
     * @param comment измененные данные комментария.
     */
    void edit(UUID bookId, UUID commentId, CommentDTO comment);

    /**
     * Удаление комментария у книги.
     *
     * @param bookId ID книги, к которой оставлен комментарий.
     * @param commentId ID удаляемого комментария.
     */
    void delete(UUID bookId, UUID commentId);

    /**
     * Получение комментария по ID.
     *
     * @param bookId ID книги.
     * @param commentId ID получаемого комментария.
     *
     * @return книгу.
     */
    CommentDTO get(UUID bookId, UUID commentId);

    /**
     * Получение всех комментариев к книге.
     *
     * @param bookID ID книги.
     *
     * @return коллекцию книг.
     */
    Collection<CommentDTO> get(UUID bookID);

}
