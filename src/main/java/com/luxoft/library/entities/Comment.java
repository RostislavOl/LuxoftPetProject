package com.luxoft.library.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Комментарий
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(schema = "library", name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String textBody;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
