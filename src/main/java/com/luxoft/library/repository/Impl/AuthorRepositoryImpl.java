package com.luxoft.library.repository.Impl;

import com.luxoft.library.entities.Author;
import com.luxoft.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final EntityManager em;

    /**
     * Добавить или обновить автора.
     *
     * @param author тело.
     * @return сохраненный автор
     */
    @Transactional
    public Author save(Author author) {
        return em.merge(author);
    }

    /**
     * Добавить или обновить автора с занесением в репозиторий.
     *
     * @param author тело.
     * @return сохраненный автор
     */
    @Transactional
    public Author saveAndFlush(Author author) {
        em.merge(author);
        em.flush();
        return author;
    }

    /**
     * Поиск всех авторов.
     *
     * @return колекцию авторов
     */
    public List<Author> findAll() {
        return em.createQuery("select author from Author author", Author.class).getResultList();
    }

    /**
     * Поиск автора по ID.
     *
     * @param id ID автора.
     * @return автора
     */
    @Transactional
    public Author findById(UUID id) {
        return em.find(Author.class, id);
    }

    /**
     * Удаление автора.
     *
     * @param author автор.
     */
    @Transactional
    public void delete(Author author) {
        em.remove(em.merge(author));
    }

    /**
     * Очистка таблицы авторов.
     */
    public void deleteAll() {
        em.createQuery("DELETE FROM Author").executeUpdate();
    }

    /**
     * Поиск автора по имени.
     *
     * @param name наименование автора.
     * @return автор
     */
    public Author findByName(String name) {
        return em.createQuery("select author from Author author where author.fullName = :name", Author.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    /**
     * Проверка сущестоввания автора по ID.
     *
     * @param name имя.
     * @return <code>true</code> если автор существует.
     */
    public boolean existsByNameAndId(String name, UUID id) {
        Author author = em.createQuery("select author from Author author where author.fullName = :name and author.id = :id", Author.class)
                .setParameter("name", name)
                .setParameter("id", id)
                .getSingleResult();
        return author != null;
    }

}
