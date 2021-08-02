package com.luxoft.library.repository.Impl;

import com.luxoft.library.entities.Genre;
import com.luxoft.library.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    private final EntityManager em;

    /**
     * Добавить или обновить жанр.
     *
     * @param genre тело.
     * @return сохраненный жанр
     */
    @Transactional
    public Genre save(Genre genre) {
        return em.merge(genre);
    }

    /**
     * Добавить или обновить жанр с занесением в репозиторий.
     *
     * @param genre тело.
     * @return сохраненный жанр
     */
    @Transactional
    public Genre saveAndFlush(Genre genre) {
        em.merge(genre);
        em.flush();
        return genre;
    }

    /**
     * Поиск всех жанров.
     *
     * @return колекцию книг
     */
    public Set<Genre> findAll() {
        return new HashSet<>(em.createQuery("select genre from Genre genre", Genre.class).getResultList());
    }

    /**
     * Поиск жанра по ID.
     *
     * @param id ID книги.
     * @return книгу
     */
    @Transactional
    public Genre findById(UUID id) {
        return em.find(Genre.class, id);
    }

    /**
     * Удаление жанра.
     *
     * @param genre жанр.
     */
    @Transactional
    public void delete(Genre genre) {
        em.remove(em.merge(genre));
    }

    /**
     * Очистка таблицы жанров.
     */
    public void deleteAll() {
        em.createQuery("DELETE FROM Genre").executeUpdate();
    }

    /**
     * Поиск жанра по названию.
     *
     * @param name наименование жанра.
     * @return жанр
     */
    public Genre findByName(String name) {
        return em.createQuery("select genre from Genre genre where genre.genreName = :name", Genre.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    /**
     * Проверка сущестоввания жанра по ID.
     *
     * @param name название жанра.
     * @return <code>true</code> если жанр существует.
     */
    public boolean existsByNameAndId(String name, UUID id) {
        Genre genre = em.createQuery("select genre from Genre genre where genre.genreName = :name and genre.id = :id", Genre.class)
                .setParameter("name", name)
                .setParameter("id", id)
                .getSingleResult();
        return genre != null;
    }

}
