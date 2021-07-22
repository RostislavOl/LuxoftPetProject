package com.luxoft.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

/**
 * DTO жанра.
 */
@Value
@Builder
public class GenreDTO {

    private UUID id;

    private String body;

}
