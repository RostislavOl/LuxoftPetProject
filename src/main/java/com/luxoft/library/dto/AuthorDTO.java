package com.luxoft.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO автора.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDTO {

    private UUID id;

    private String fullName;

}
