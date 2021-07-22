package com.luxoft.library.repository;

import com.luxoft.library.entities.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository {

    Comment save(Comment comment);

    List<Comment> findAll();

    Comment findById(UUID id);

    void delete(Comment comment);

    void deleteAll();

}
