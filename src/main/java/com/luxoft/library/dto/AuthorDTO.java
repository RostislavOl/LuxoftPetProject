package com.luxoft.library.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

/**
 * DTO автора.
 */
@Value
@Builder
public class AuthorDTO {

    UUID id;

    String fullName;

}
