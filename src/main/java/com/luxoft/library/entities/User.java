package com.luxoft.library.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Пользователь
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(schema = "library", name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String login;

    @Column
    private String password;

    @Column(name = "comment_list")
    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

}
