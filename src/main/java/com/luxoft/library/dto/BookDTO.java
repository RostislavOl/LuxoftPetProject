package com.luxoft.library.dto;

import com.luxoft.library.entities.Genre;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.UUID;

/**
 * DTO книги.
 */
@Value
@Builder
public class BookDTO {

    UUID id;

    String name;

    List<UUID> author;

    List<UUID> comment;

    Genre genre;

}
