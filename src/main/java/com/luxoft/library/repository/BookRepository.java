package com.luxoft.library.repository;

import com.luxoft.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    /**
     * Проверка сущестоввания книги по ID.
     *
     * @param name название книги.
     *
     * @return <code>true</code> если книга существует.
     */
    boolean existsByNameAndId(String name, UUID id);

    /**
     * Поиск книги по названию.
     *
     * @param name название книги.
     * @return книгу
     */
    Optional<Book> findByName(String name);

}
