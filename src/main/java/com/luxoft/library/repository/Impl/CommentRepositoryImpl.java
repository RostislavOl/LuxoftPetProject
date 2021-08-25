package com.luxoft.library.repository.Impl;

import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Comment;
import com.luxoft.library.repository.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

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
     * @param comment данные книги.
     * @return сохраненную книгу
     */
    @Transactional
    public Comment save(Comment comment){
        return em.merge(comment);
    }

    /**
     * Поиск всех комментариев.
     *
     * @return колекцию комментариев
     */
    public List<Comment> findAll(){
        return em.createQuery("select comment from Comment comment", Comment.class).getResultList();
    }

    /**
     * Поиск комментария по ID.
     *
     * @param id ID комментария.
     * @return комментарий
     */
    public Comment findById(UUID id){
        return em.find(Comment.class, id);
    }

    /**
     * Удаление комментария.
     *
     * @param comment комментарий.
     */
    public void delete(Comment comment) {
        em.remove(em.merge(comment));
    }

    /**
     * Удаление всех книг.
     */
    public void deleteAll() {
        em.createQuery("DELETE FROM Comment").executeUpdate();
    }

}
