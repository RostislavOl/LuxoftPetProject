package com.luxoft.library.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

/**
 * DTO жанра.
 */
@Value
@Builder
public class GenreDTO {

    UUID id;

    String body;

}
