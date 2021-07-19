package com.luxoft.library.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * DTO комментария.
 */
@Getter
@Builder
public class NewCommentDTO {

    private String textBody;

    private UUID bookId;

}
