package com.luxoft.library.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class BookDTO {

    private UUID id;

    private String name;

}
