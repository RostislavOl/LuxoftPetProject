package com.luxoft.library.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Автор
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(schema = "library", name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "faullname")
    private String fullName;

}
