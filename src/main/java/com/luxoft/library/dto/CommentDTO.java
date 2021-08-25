package com.luxoft.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO комментария.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private UUID id;

    private String textBody;

    private UUID bookId;

}
