package com.luxoft.library.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class BookDTO {

    private UUID id;

    private String name;

}
