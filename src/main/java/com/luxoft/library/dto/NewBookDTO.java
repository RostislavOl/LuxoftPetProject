package com.luxoft.library.dto;

import com.luxoft.library.entities.Genre;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

/**
 * DTO книги.
 */
@Getter
@Builder
public class NewBookDTO {

    private String name;

    private List<UUID> author;

    private List<UUID> comment;

    private Genre genre;

}
