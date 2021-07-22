package com.luxoft.library.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * DTO жанра.
 */
@Getter
@Builder
public class NewGenreDTO {

    private UUID id;

    private String body;

}
