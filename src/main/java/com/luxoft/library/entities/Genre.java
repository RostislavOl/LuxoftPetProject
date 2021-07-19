package com.luxoft.library.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Список жанров
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Table(schema = "library", name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "genre_name")
    private String genreName;

}
