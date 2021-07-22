package com.luxoft.library.repository;

import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class GenreRepository {

    private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Добавить или обновить жанр.
     *
     * @param genre тело.
     * @return сохраненный жанр
     */
    @Transactional
    public Genre save(Genre genre){
        return em.merge(genre);
    }

    /**
     * Добавить или обновить жанр с занесением в репозиторий.
     *
     * @param genre тело.
     * @return сохраненный жанр
     */
    @Transactional
    public Genre saveAndFlush(Genre genre){
        em.merge(genre);
        em.flush();
        return genre;
    }

    /**
     * Поиск всех жанров.
     *
     * @return колекцию книг
     */
    public List<Genre> findAll(){
        return em.createQuery("select genre from Genre genre", Genre.class).getResultList();
    }

    /**
     * Поиск жанра по ID.
     *
     * @param id ID книги.
     * @return книгу
     */
    public Genre findById(UUID id){
        return em.createQuery("select genre from Genre genre where genre.id = :id", Genre.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     * Удаление жанра.
     *
     * @param genre жанр.
     */
    public void delete(Genre genre) {
        em.remove(em.merge(genre));
    }

    /**
     * Очистка таблицы жанров.
     */
    public void deleteAll() {
        List<Genre> genres = findAll();
        for (Genre genre: genres) {
            em.remove(em.merge(genre));
        }
    }

    /**
     * Поиск жанра по названию.
     *
     * @param name наименование жанра.
     * @return жанр
     */
    public Genre findByName(String name){
        return em.createQuery("select genre from Genre genre where genre.genreName = :name", Genre.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    /**
     * Проверка сущестоввания жанра по ID.
     *
     * @param name название жанра.
     *
     * @return <code>true</code> если жанр существует.
     */
    public boolean existsByNameAndId(String name, UUID id){
        Genre genre = em.createQuery("select genre from Genre genre where genre.genreName = :name and genre.id = :id", Genre.class)
                .setParameter("name", name)
                .setParameter("id", id)
                .getSingleResult();
        return genre != null;
    }

}
