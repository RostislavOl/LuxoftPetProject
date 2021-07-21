package com.luxoft.library.repository;

import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
public class CommentRepository {

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
        return em.createQuery("select comment from Comment comment where comment.id = :id", Comment.class)
                .setParameter("id", id)
                .getSingleResult();
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
        List<Comment> comments = em.createQuery("select comment from Comment comment", Comment.class).getResultList();
        for (Comment comment : comments) {
            em.remove(em.merge(comment));
        }
    }

}
