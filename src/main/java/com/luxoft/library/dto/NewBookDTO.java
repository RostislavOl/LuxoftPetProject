package com.luxoft.library.dto;

import com.luxoft.library.entities.Author;
import com.luxoft.library.entities.Comment;
import com.luxoft.library.entities.Genre;
import lombok.Getter;

import java.util.List;

/**
 * DTO книги.
 */
@Getter
public class NewBookDTO {

    private String name;

    private List<Author> author;

    private List<Comment> comment;

    private Genre genre;

}
