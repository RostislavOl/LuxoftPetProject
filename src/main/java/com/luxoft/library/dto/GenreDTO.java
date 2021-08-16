package com.luxoft.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO жанра.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreDTO {

    private UUID id;

    private String genreName;

}
