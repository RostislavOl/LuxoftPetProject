package com.luxoft.library.repository;

import com.luxoft.library.entities.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class BookRepository{

    private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Сохранение книги.
     *
     * @param book данные книги.
     * @return сохраненную книгу
     */
    @Transactional
    public Book save(Book book){
        return em.merge(book);
    }

    /**
     * Поиск всех книг.
     *
     * @return колекцию книг
     */
    public List<Book> findAll(){
        return em.createQuery("select book from Book book", Book.class).getResultList();
    }

    /**
     * Поиск книги по ID.
     *
     * @param id ID книги.
     * @return книгу
     */
    public Book findById(UUID id){
        return em.createQuery("select book from Book book where book.id = :id", Book.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     * Удаление книги.
     *
     * @param book книга.
     */
    public void delete(Book book) {
        em.remove(em.merge(book));
    }

    /**
     * Удаление всех книг.
     */
    public void deleteAll() {
        List<Book> books = findAll();
        for (Book book: books) {
            em.remove(em.merge(book));
        }
    }

    /**
     * Поиск книги по названию.
     *
     * @param name название книги.
     * @return книгу
     */
    public Book findByName(String name){
        return em.createQuery("select book from Book book where book.name = :name", Book.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    /**
     * Проверка сущестоввания книги по ID.
     *
     * @param name название книги.
     *
     * @return <code>true</code> если книга существует.
     */
    public boolean existsByNameAndId(String name, UUID id){
        Book book = em.createQuery("select book from Book book where book.name = :name and book.id = :id", Book.class)
                .setParameter("name", name)
                .setParameter("id", id)
                .getSingleResult();
        return book != null;
    }

}
