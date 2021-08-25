package com.luxoft.library.dto;

import com.luxoft.library.entities.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * DTO книги.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    private UUID id;

    private String name;

    private List<UUID> author;

    private List<UUID> comment;

    private Genre genre;

}
