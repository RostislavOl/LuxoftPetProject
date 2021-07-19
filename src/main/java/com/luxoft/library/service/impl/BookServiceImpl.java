package com.luxoft.library.service.impl;

import com.luxoft.library.entities.Author;
import com.luxoft.library.entities.Book;
import com.luxoft.library.entities.Comment;
import com.luxoft.library.exceptions.DataNotFoundedExceptions;
import com.luxoft.library.repository.AuthorRepository;
import com.luxoft.library.repository.BookRepository;
import com.luxoft.library.repository.CommentRepository;
import com.luxoft.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.luxoft.library.exceptions.constants.MessageResource.BOOK_BY_NAME_NOT_FOUND;

/**
 * Имплементация книжного сервиса.
 */
@RequiredArgsConstructor
@Component
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    private final AuthorRepository authorRepository;

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public Book save(Book entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public boolean isBookExists(String bookName, Book entity) {
        return repository.existsByNameAndId(bookName, entity.getId());
    }

    @Override
    public Optional<? extends Book> get(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<? extends Book> get() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(Book book) {
        repository.delete(book);
    }

    @Override
    public Book findByName(String name) {
        return repository.findByName(name).orElseThrow(
                () -> new DataNotFoundedExceptions(BOOK_BY_NAME_NOT_FOUND, name)
        );
    }

    public List<Author> getAuthorList(List<UUID> authorIds){
        List<Author> authors= new ArrayList<>();
        for (UUID authorId: authorIds){
            Optional<Author> optional = authorRepository.findById(authorId);
            optional.ifPresent(authors::add);
        }
        return authors;
    }

    public List<Comment> getComments(List<UUID> commentIds){
        List<Comment> comments = new ArrayList<>();
        if (!commentIds.isEmpty()) {

            for (UUID commentId : commentIds) {
                Optional<Comment> optional = commentRepository.findById(commentId);
                optional.ifPresent(comments::add);
            }
        }
        return comments;
    }
}
