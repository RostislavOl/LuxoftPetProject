package com.luxoft.library.service;

import com.luxoft.library.entities.Book;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    /**
     * Сохранение книги.
     *
     * @param entity сущность книги.
     *
     * @return сохраненную книги.
     */
    Book save(Book entity);

    /**
     * Проверка существоования книги.
     *
     * @param bookName название книги.
     * @param entity книга.
     *
     * @return <code>true</code> если книга существует.
     */
    boolean isBookExists(String bookName, Book entity);

    /**
     * Получение книги ID.
     *
     * @param bookId ID получаемой книги.
     *
     * @return книгу.
     */
    Optional<? extends Book> get(UUID bookId);

    /**
     * Получение списка книг.
     *
     * @return книгу.
     */
    Collection<? extends Book> get();

    /**
     * Удалить книгу.
     *
     * @param book удаляемая сущность.
     */
    void delete(Book book);

    /**
     * Найти книгу по названию.
     *
     * @param name название книги.
     */
    Book findByName(String name);

}
