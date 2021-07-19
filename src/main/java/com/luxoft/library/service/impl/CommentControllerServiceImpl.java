package com.luxoft.library.service.impl;

import com.luxoft.library.dto.CommentDTO;
import com.luxoft.library.dto.NewCommentDTO;
import com.luxoft.library.service.BookService;
import com.luxoft.library.service.CommentControllerService;
import com.luxoft.library.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

/**
 * Имплементация контроллера комментариев.
 */
@RequiredArgsConstructor
@Component
public class CommentControllerServiceImpl implements CommentControllerService {

    private final BookService bookService;

    private final CommentService commentService;

    @Override
    public CommentDTO add(UUID bookId, NewCommentDTO comment) {
        return null;
    }

    @Override
    public void edit(UUID bookId, UUID commentId, NewCommentDTO comment) {

    }

    @Override
    public void delete(UUID bookId, UUID commentId) {

    }

    @Override
    public CommentDTO get(UUID bookId, UUID commentId) {
        return null;
    }

    @Override
    public Collection<CommentDTO> get(UUID bookID) {
        return null;
    }
}
