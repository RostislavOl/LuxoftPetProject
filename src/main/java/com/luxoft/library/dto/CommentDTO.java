package com.luxoft.library.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

/**
 * DTO комментария.
 */
@Value
@Builder
public class CommentDTO {

    private UUID id;

    private String commentBody;

}
